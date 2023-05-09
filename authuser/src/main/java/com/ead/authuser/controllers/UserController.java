package com.ead.authuser.controllers;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.dto.view.UserEntryView;
import com.ead.authuser.dto.view.UserReturnView;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @JsonView(UserReturnView.Default.class)
    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(
            @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<UserDto> userDtoPage = userService.findAll(pageable);

        return ResponseEntity.status(200).body(userDtoPage);
    }

    @JsonView(UserReturnView.Default.class)
    @GetMapping(path = "/{userId}")
    public UserDto getOneUser(@PathVariable UUID userId) {
        return userService.getOneUser(userId);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok().body("User deleted successfully");
    }

    @JsonView(UserReturnView.Default.class)
    @PutMapping(path = "/{userId}")
    public UserDto updateUser(
            @PathVariable UUID userId,
            @RequestBody
            @JsonView(UserEntryView.UpdateUser.class)
            UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }

    @PutMapping(path = "/{userId}/password")
    public ResponseEntity<String> updatePassword(
            @PathVariable UUID userId,
            @RequestBody
            @Validated(UserEntryView.UpdatePassword.class)
            @JsonView(UserEntryView.UpdatePassword.class)
            UserDto userDto) {
        userService.updatePassword(userId, userDto);
        return ResponseEntity.ok().body("Password updated successfully.");
    }

    @PutMapping(path = "/{userId}/image")
    @JsonView(UserReturnView.Default.class)
    public UserDto updateImage(
            @PathVariable UUID userId,
            @RequestBody
            @JsonView(UserEntryView.UpdateImage.class)
            @Validated(UserEntryView.UpdateImage.class)
            UserDto userDto) {
        return userService.updateImage(userId, userDto);
    }
}

