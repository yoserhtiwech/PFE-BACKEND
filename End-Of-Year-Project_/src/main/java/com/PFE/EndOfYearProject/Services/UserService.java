package com.PFE.EndOfYearProject.Services;

import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Users;
import com.PFE.EndOfYearProject.dto.AuthenticationDto;
import java.util.List;
import java.util.Optional;

public interface UserService {
    // UserDto login(UserDto dto) ;
    UserDto login(AuthenticationDto request);
    Optional<Users> findByEmail(String email);
    Users findByLastName(String lastname);
    void saveUser(UserDto userDto);
    List<UserDto> findAllUsers();

   // Users saveUser(UserDto userDto);

    UserDto findUseById(long userId );

    void updateUser(UserDto user);

    void delete(long userId);

    List<UserDto> searchUsers(String query);
}
