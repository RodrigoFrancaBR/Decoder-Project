package com.ead.authuser.services.impl;

import com.ead.authuser.exceptions.UserNotFoundException;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserModel> findAllUsers() {
        var users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return users;

    }

}
