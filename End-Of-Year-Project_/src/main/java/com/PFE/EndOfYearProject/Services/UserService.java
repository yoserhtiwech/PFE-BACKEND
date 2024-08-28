package com.PFE.EndOfYearProject.Services;

import com.PFE.EndOfYearProject.RequestResponse.UserRequest;
import com.PFE.EndOfYearProject.dto.AuthenticationResponse;
import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.CompleteRegistrationRequest;
import com.PFE.EndOfYearProject.models.RegistrationRequest;
import com.PFE.EndOfYearProject.models.Users;
import com.PFE.EndOfYearProject.dto.AuthenticationDto;
import jakarta.mail.MessagingException;
import com.PFE.EndOfYearProject.RequestResponse.UserUpdateRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    AuthenticationResponse login(AuthenticationDto request);
   void completeRegistration(CompleteRegistrationRequest request);
    String generateAndSaveActivationToken(Users user);
   void register(RegistrationRequest request) throws MessagingException;
   void activateAccount(String token) throws MessagingException;

    List<UserDto> findAllUsers();

    UserDto getUserDetailsForToken(String token);

    List<UserDto> findGroupeUsers();
     Integer createUser(UserRequest userDto) ;


    Integer updateUser(Authentication authentication, UserUpdateRequest userUpdateRequest);

    Integer adduser(UserRequest userRequest);

    void removeMemberFromGroup(Integer id);

    UserDto findUser(Integer userId);
}
