package com.PFE.EndOfYearProject.Mapper;

import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Users;

public class UserMapper {

    public static Users mapToUser(UserDto user) {
        Users userDto= Users.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .poste(user.getPoste())
                .build();
        return userDto;
    }

    public static UserDto mapToUserDto(Users user) {
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .poste(user.getPoste())
                .build();
        return userDto;
    }
    }
