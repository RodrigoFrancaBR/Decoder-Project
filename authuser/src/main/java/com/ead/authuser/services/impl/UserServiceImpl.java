package com.ead.authuser.services.impl;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.exceptions.UserConflictException;
import com.ead.authuser.exceptions.UserNotFoundException;
import com.ead.authuser.mapper.UserMapper;
import com.ead.authuser.mapper.UserMapperRegister;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.Boolean.TRUE;
import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserMapperRegister userMapperRegister;
    private final UserRepository userRepository;

    @Override
    public List<UserDto> findAll() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    public UserDto getOneUser(UUID userId) {
        return userMapper.toDto(findUserById(userId));
    }

    @Override
    public void deleteById(UUID userId) {
        userRepository.deleteById(findUserById(userId).getUserId());
    }

    @Override
    public UserDto updateUser(UUID userId, UserDto userDto) {
        var userModel = findUserById(userId);
        userModel.setFullName(userDto.getFullName());
        userModel.setPhoneNumber(userDto.getPhoneNumber());
        userModel.setCpf(userDto.getCpf());
        return userMapper.toDto(userRepository.save(userModel));
    }

    @Override
    public void updatePassword(UUID userId, UserDto userDto) {
        var userModel = findUserById(userId);

        if (!userDto.getOldPassword().equals(userModel.getPassword())) {
            throw new UserConflictException("Error: Mismatched old password");
        }

        userModel.setPassword(userDto.getPassword());
        userRepository.save(userModel);
    }

    @Override
    public UserDto updateImage(UUID userId, UserDto userDto) {
        var userModel = findUserById(userId);
        userModel.setImageUrl(userDto.getImageUrl());
        return userMapper.toDto(userRepository.save(userModel));
    }

    @Override
    public UserDto save(UserDto userDto) {
        validUsername(userDto.getNickName());
        validEmail(userDto.getEmail());
        var userModel = userMapperRegister.toModel(userDto);
        return userMapper.toDto(userRepository.save(userModel));
    }

    private UserModel findUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private void validEmail(String email) {
        of(userRepository.existsByEmail(email))
                .filter(TRUE::equals)
                .map(exists -> throwUserConflictException("Error: email is Already Taken!"));
    }

    private void validUsername(String userName) {
        of(userRepository.existsByUserName(userName))
                .filter(TRUE::equals)
                .map(exists -> throwUserConflictException("Error: Username is Already Taken!"));
    }

    private UserConflictException throwUserConflictException(String msg) {
        throw new UserConflictException(msg);
    }

}
