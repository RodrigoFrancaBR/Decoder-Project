package com.ead.authuser.controllers.api;

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

import com.ead.authuser.controllers.util.LinksFactory;
import com.ead.authuser.controllers.views.UserEntryView;
import com.ead.authuser.controllers.views.UserReturnView;
import com.ead.authuser.model.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final UserService userService;
	private final LinksFactory factory;

	@JsonView(UserReturnView.Default.class)
	@PostMapping(path = "/signup")
	public ResponseEntity<UserModel> registerUser(
			@RequestBody @Validated(UserEntryView.RegisterUser.class) 
			@JsonView(UserEntryView.RegisterUser.class) 
			UserModel userDto) {

		var savedDto = userService.save(userDto);
		var location = factory.buildUriLocation(savedDto.getUserId());
		return status(CREATED).header(LOCATION, location).body(savedDto); 
	}

}
