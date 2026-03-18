package com.robson.mapper;

import com.robson.dto.UserRequestDto;
import com.robson.dto.UserResponseDto;
import com.robson.entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDto dto) {
        User user = new User();
        user.name = dto.name;
        user.email = dto.email;
        return user;
    }

    public static UserResponseDto toResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.id = user.id;
        dto.name = user.name;
        dto.email = user.email;
        return dto;
    }

    public static void updateEntity(User user, UserRequestDto dto) {
        user.name = dto.name;
        user.email = dto.email;
    }
}