package com.PFE.EndOfYearProject.Services.impl;

import com.PFE.EndOfYearProject.Mapper.UserMapper;
import com.PFE.EndOfYearProject.Repository.NumberRepository;
import com.PFE.EndOfYearProject.Repository.RoleRepository;
import com.PFE.EndOfYearProject.Repository.TokenRepository;
import com.PFE.EndOfYearProject.Repository.UserRepository;


import com.PFE.EndOfYearProject.RequestResponse.UserRequest;
import com.PFE.EndOfYearProject.RequestResponse.UserUpdateRequest;
import com.PFE.EndOfYearProject.Security.JwtService;
import com.PFE.EndOfYearProject.Services.EmailService;
import com.PFE.EndOfYearProject.Repository.GroupeRepository;
import com.PFE.EndOfYearProject.Services.UserService;
import com.PFE.EndOfYearProject.dto.*;

import com.PFE.EndOfYearProject.exception.InvalidPasswordException;
import com.PFE.EndOfYearProject.models.*;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service

@RequiredArgsConstructor
public class UsersServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;
    private final NumberRepository numberRepository;
    private final GroupeRepository groupeRepository ;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;
    @Override
    public AuthenticationResponse login(AuthenticationDto request)
    { System.out.print(request.getEmail());
        System.out.print(request.getPassword());
        var auth = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )

        ); System.out.print("hello2");

       var claims = new HashMap<String, Object>();
        var user = ((Users) auth.getPrincipal());
        claims.put("fullName", user.getFullName());
        claims.put("id",user.getId());

        var jwtToken = jwtService.generateToken(claims, (Users) auth.getPrincipal());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();



    }
    @Override
    public void register(RegistrationRequest request) throws MessagingException {
        Users user = new Users();
        user.setEmail(request.getEmail());
      //  user.setNumber(numberRepository.);
        user.setEnabled(false);
        user.setAccountLocked(false);

        roles role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);

        userRepository.save(user);

        String token = generateAndSaveActivationToken(user);
        sendValidationEmail(user);
    }

    @Override
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
                // todo exception has to be defined
                .orElseThrow(() -> new RuntimeException("Invalid token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been send to the same email address");
        }

        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }
    private void sendValidationEmail(Users user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        String registrationUrl = "http://localhost:4200/authentication/side-register?token=" + newToken;

        emailService.sendEmail(
                user.getEmail(),
                "Complete Your Registration",
                "Please complete your registration by clicking the following link:"+
                registrationUrl
        );
        // emailService.sendEmail(
        //        user.getEmail(),
        //       user.getFullName(),
        //     EmailTemplateName.ACTIVATE_ACCOUNT,
        //    activationUrl,
        //    newToken,
        //    "Account activation"
        //  );
    }
    @Override
    public void completeRegistration(CompleteRegistrationRequest request) {
        Token token = tokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        Users user= userRepository.findById(token.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setFullname(request.getFullname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);

        userRepository.save(user);
        tokenRepository.delete(token); // Token is no longer needed after registration
         }
    public String generateAndSaveActivationToken(Users user) {
        // Generate a token
        String generatedCode = generateActivationCode(6);

        // Constructing the payload with user data
        String userEmail = user.getEmail();
        //String userNumber = String.valueOf(user.getNumber().getNum());
      //  String userGroup = user.getGroupsddd().getName();


        String payload = userEmail +  "|" + generatedCode;

        // Encoding the payload
        String encodedPayload = Base64.getEncoder().encodeToString(payload.getBytes());

        var token = Token.builder()
                .token(encodedPayload)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();

        tokenRepository.save(token);

        return encodedPayload;
    }
    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }
    @Override
    public UserDto getUserDetailsForToken(String token) {
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        Users user = savedToken.getUser();
        return UserDto.builder()
                .email(user.getEmail())
                .fullname(user.getFullName())
                //.roles(user.getRoles())
                .build();
    }
    @Override
    public List<UserDto> findGroupeUsers() {
        // Step 1: Retrieve the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = (Users) authentication.getPrincipal();

        // Step 2: Get the group of the authenticated user
        String userGroup = currentUser.getGroups().getName(); // Updated to singular getGroupe()

        // Step 3: Fetch all users who belong to that group
        if(Objects.equals(currentUser.getRole().getName(), "Admin")){
            List<UserDto> groupUsers=userRepository.findAll().stream().filter(users -> users.isEnabled())
                    .map(user -> {
                        UserDto dto = new UserDto();
                        dto.setId(user.getId());
                        dto.setEmail(user.getEmail());
                        dto.setPoste(user.getPoste());
                        dto.setPassword(user.getPassword());
                        dto.setFullname(user.getFullName());
                        dto.setCreatedDate(user.getCreatedDate());
                        dto.setRoles(user.getRole().getName());
                        if(user.getNumber()!=null){
                        dto.setNumbers(user.getNumber().getNum());}
                        return dto;
                    }).collect(Collectors.toList());
            return groupUsers;
        }else{
         Groupes groupes= groupeRepository.findByName(userGroup);
        List<UserDto> groupUsers = groupes.getUsers().stream()
                .map(user -> {
                    UserDto dto = new UserDto();
                    dto.setId(user.getId());
                    dto.setEmail(user.getEmail());
                    dto.setPoste(user.getPoste());
                    dto.setPassword(user.getPassword());
                    dto.setFullname(user.getFullName());
                    dto.setCreatedDate(user.getCreatedDate());
                    dto.setRoles(user.getRole().getName());
                    dto.setNumbers(user.getNumber().getNum());
                    return dto;
                }).collect(Collectors.toList());
        System.out.println(groupUsers);

        // Convert to UserDto
        return groupUsers;}
    }
    @Override
    public Integer createUser(UserRequest userDto) {
        Users user = new Users();
        //user.setNumber(NumberMapper.convertToNumber(userDto.getNumbers()));
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
      //  user.setRole(RoleMapper.convertToRole(userDto.getRoles())); // or set roles based on userDto
        return userRepository.save(user).getId();
    }
    @Override
    public Integer updateUser(Authentication authentication, UserUpdateRequest updateUserRequest) {

        Users currentUser = (Users) authentication.getPrincipal();

    if (passwordEncoder.encode(updateUserRequest.getOldpassword())!=currentUser.getPassword()){
        currentUser.setPassword(passwordEncoder.encode(updateUserRequest.getNewpassword()));
    }
    else{
        throw new InvalidPasswordException("Old password is incorrect");
    }
        if (!Objects.equals(updateUserRequest.getFullname(), currentUser.getFullName())) {
            currentUser.setFullname(updateUserRequest.getFullname());
        }
        if (!Objects.equals(updateUserRequest.getPoste(), currentUser.getPoste())) {
            currentUser.setPoste(updateUserRequest.getPoste());
        }

        // Update other fields as needed

        return userRepository.save(currentUser).getId();}
    @Override
    public Integer adduser(UserRequest userRequest) {return null;}
    @Override
    public void removeMemberFromGroup(Integer id) {}
    @Override
    public UserDto findUser(Integer userId) {

            // Find the user by ID using the repository
            Optional<Users> userOptional = userRepository.findById(userId);

            // If the user is found, map it to UserDto and return it; otherwise, throw an exception or return null
            return userOptional.map(UserMapper::mapToUserDto)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        }
    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .filter(user -> user.isEnabled())  // Filter out users who are not enabled
                .map(user -> {
                    UserDto dto = new UserDto();
                    dto.setId(user.getId());
                    dto.setEmail(user.getEmail());
                    dto.setPoste(user.getPoste());
                    dto.setPassword(user.getPassword());
                    dto.setFullname(user.getFullName());
                    dto.setCreatedDate(user.getCreatedDate());
                    dto.setRoles(user.getRole().getName());
                    dto.setNumbers(user.getNumber().getNum());
                    return dto;
                })
                .collect(Collectors.toList());
    }




}

