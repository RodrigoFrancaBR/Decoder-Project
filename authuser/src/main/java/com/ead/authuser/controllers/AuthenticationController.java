package com.ead.authuser.controllers;

import static com.ead.authuser.controllers.ControllerHelper.buildUriLocation;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		var location = buildUriLocation(savedDto.getUserId());

		return status(CREATED).header(LOCATION, location).body(savedDto);
	}

}
