package com.ead.authuser.services.impl;

import com.ead.authuser.exceptions.UserNotFoundException;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.Boolean.*;
import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private IllegalArgumentException throwException(String msg) {
        return new IllegalArgumentException(msg);
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserModel findById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("User not found"));
    }

    @Override
    public void deleteById(UUID userId) {
        of(findById(userId))
                .ifPresent((userModel)->userRepository.deleteById(userModel.getUserId()));
    }

    @Override
    public void save(UserModel userModel) {
        validUsername(userModel.getUserName());
        validEmail(userModel.getEmail());
        userRepository.save(userModel);
    }

    private void validEmail(String email) {
        of(userRepository.existsByEmail(email))
                .filter(TRUE::equals)
                .map(exists->throwException("Error: email is Already Taken!"));
    }

    private void validUsername(String userName) {
        of(userRepository.existsByUserName(userName))
                .filter(TRUE::equals)
                .map(exists-> throwException("Error: Username is Already Taken!"));
    }

}
