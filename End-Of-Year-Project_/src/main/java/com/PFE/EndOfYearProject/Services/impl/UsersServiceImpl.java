package com.PFE.EndOfYearProject.Services.impl;

import com.PFE.EndOfYearProject.Repository.RoleRepository;
import com.PFE.EndOfYearProject.Repository.UserRepository;
import com.PFE.EndOfYearProject.Services.UserService;
import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Users;
import com.PFE.EndOfYearProject.models.roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.PFE.EndOfYearProject.Mapper.UserMapper.mapToUser;
import static com.PFE.EndOfYearProject.Mapper.UserMapper.mapToUserDto;

@Service
public class UsersServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UsersServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public Users findByEmail(String email) {
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
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
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

