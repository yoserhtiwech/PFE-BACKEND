package com.PFE.EndOfYearProject.Mapper;

import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Users;

public class UserMapper {

    public static Users mapToUser(UserDto user) {
        return Users.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .password(user.getPassword())
                .poste(user.getPoste())
                .build();
    }

    public static UserDto mapToUserDto(Users user) {
        return UserDto.builder()
                .id(user.getId())
                .fullname(user.getFullName())
                .email(user.getEmail())
                .password(user.getPassword())
                .poste(user.getPoste())
                .roles(user.getRole().getName())
                .numbers(user.getNumber().getNum())
                .groupe(user.getGroups().getName())
                .build();
    }
    }
