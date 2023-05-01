package com.ead.authuser.controllers;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.dto.view.UserEntryView;
import com.ead.authuser.dto.view.UserReturnView;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @JsonView(UserReturnView.Default.class)
    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> registerUser(
            @RequestBody
            @Validated(UserEntryView.RegisterUser.class)
            @JsonView(UserEntryView.RegisterUser.class)
            UserDto userDto){
        var savedDto = userService.save(userDto);
        return ResponseEntity.status(CREATED).body(savedDto);
    }
}
