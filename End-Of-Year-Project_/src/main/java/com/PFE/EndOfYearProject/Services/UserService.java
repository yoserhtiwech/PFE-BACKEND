package com.PFE.EndOfYearProject.Services;

import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Users;

import java.util.List;

public interface UserService {
    Users findByEmail(String email);
    Users findByLastName(String lastname);
    void saveUser(UserDto userDto);
    List<UserDto> findAllUsers();

   // Users saveUser(UserDto userDto);

    UserDto findUseById(long userId );

    void updateUser(UserDto user);

    void delete(long userId);

    List<UserDto> searchUsers(String query);
}
