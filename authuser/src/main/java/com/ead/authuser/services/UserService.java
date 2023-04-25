package com.ead.authuser.services;

import com.ead.authuser.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDto> findAll();

   UserDto getOneUser(UUID userId);

    void deleteById(UUID userId);

    UserDto save(UserDto userDto);

    UserDto updateUser(UUID userId, UserDto userDto);

    void updatePassword(UUID userId, UserDto userDto);

    UserDto updateImage(UUID userId, UserDto userDto);
}
