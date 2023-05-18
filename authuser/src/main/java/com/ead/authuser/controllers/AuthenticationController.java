package com.ead.authuser.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.dto.view.UserEntryView;
import com.ead.authuser.dto.view.UserReturnView;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.RequiredArgsConstructor;

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
            UserDto userDto) {
    	
        var savedDto = userService.save(userDto);
        var location = ControllerUriHelper.buildUriLocation(savedDto.getUserId());
                
        return ResponseEntity        		
        		.status(CREATED)
        		.header(HttpHeaders.LOCATION, location.toString())        		
        		.body(savedDto);
    }

}
