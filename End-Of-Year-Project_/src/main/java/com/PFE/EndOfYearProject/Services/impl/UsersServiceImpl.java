package com.PFE.EndOfYearProject.Services.impl;

import com.PFE.EndOfYearProject.Repository.RoleRepository;
import com.PFE.EndOfYearProject.Repository.UserRepository;
import com.PFE.EndOfYearProject.Security.JwtService;
import com.PFE.EndOfYearProject.Services.UserService;
import com.PFE.EndOfYearProject.dto.AuthenticationDto;
import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Users;
import com.PFE.EndOfYearProject.models.roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.PFE.EndOfYearProject.Mapper.UserMapper.mapToUser;
import static com.PFE.EndOfYearProject.Mapper.UserMapper.mapToUserDto;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UserService {
    private JwtService jwtService;
    private  AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
   /* @Autowired
    private PasswordEncoder passwordEncoder;*/


    @Override
    public UserDto login(AuthenticationDto request) {var auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );

        var claims = new HashMap<String, Object>();
        var user = ((Users) auth.getPrincipal());
        claims.put("fullName", user.getFullName());

        var jwtToken = jwtService.generateToken(claims, (Users) auth.getPrincipal());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }



    // @Override
    /*public UserDto login(UserDto dto) {
        Users user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(dto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);}
*/
    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users findByLastName(String lastname) {
        return userRepository.findByUsername(lastname);
    }


    @Override
    public void saveUser(UserDto userDto) {
        Users user=new Users();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
       // user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        System.out.println("Encoded Password: " + user.getPassword() );
        user.setPoste(user.getPoste());
        roles role= roleRepository.findByName("AGENT");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<Users> users= userRepository.findAll();
        return users.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
    }

  //  @Override
  //  public Users saveUser(UserDto userDto) {
  //  Users user = mapToUser(userDto);

   //     return userRepository.save(user);
  //  }

    @Override
    public UserDto findUseById(long userId) {
        Users user=userRepository.findById(userId).get();
        return mapToUserDto(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        Users user=mapToUser(userDto);
        userRepository.save(user);
    }

    @Override
    public void delete(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> searchUsers(String query) {
        List<Users> users= userRepository.searchUsers(query);
        return  users.stream().map(user ->mapToUserDto(user)).collect(Collectors.toList());
    }

     }

