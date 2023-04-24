package com.ead.authuser.controllers;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.mapper.UserMapper;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserModel>getAllUsers(){
        return userService.findAll();
    }

    @GetMapping(path = "/{userId}")
    public UserModel getOneUser(@PathVariable UUID userId) {
        return userService.findById(userId);
    }
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity deleteUser(@PathVariable UUID userId){
        userService.deleteById(userId);
        return ResponseEntity.ok().build();
    }
    @PostMapping
    public void CreateOneUser(@RequestBody UserDto userDto){
            userService.save(userMapper.toModel(userDto));
    }
}
