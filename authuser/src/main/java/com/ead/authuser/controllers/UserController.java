package com.ead.authuser.controllers;

import static com.ead.authuser.controllers.ControllerHelper.buildLinkWithSelfAndRelation;
import static com.ead.authuser.controllers.ControllerHelper.buildLinkWithSelfRelation;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.dto.view.UserEntryView;
import com.ead.authuser.dto.view.UserReturnView;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@JsonView(UserReturnView.Default.class)
	@GetMapping
	public Page<UserDto> findAll(
			@PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) 
			Pageable pageable) {
		
		 return userService.findAll(pageable)		
		 .map(dto->dto.add(buildLinkWithSelfRelation(UserController.class, dto.getUserId())));		 
	}
	
	@JsonView(UserReturnView.Default.class)
	@GetMapping(path = "/{userId}")
	public UserDto getOneUser(@PathVariable UUID userId) {
		
		var oneUser = userService.getOneUser(userId);
		var links = buildLinkWithSelfAndRelation(UserController.class, userId);		
		return oneUser.setLinkWithSelfAndRelation(links.get("linkWithSelfRelation"), 
				links.get("linkWithRelation"));
	}

	@JsonView(UserReturnView.Default.class)
	@GetMapping(path = "byEmailAndStatusAndType")
	public ResponseEntity<Page<UserDto>> findAllByEmailAndStatusAndType(
			@PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC)
			Pageable pageable,
			@JsonView(UserEntryView.FilterUser.class)
			UserDto userDto) {
		
		var userDtoPage = userService.findAllByEmailAndStatusAndType(pageable, userDto);
		return status(200).body(userDtoPage);
	}
	
	@JsonView(UserReturnView.Default.class)
	@GetMapping(path = "byEmailOrStatusOrType")
	public ResponseEntity<Page<UserDto>> findAllByEmailOrStatusOrType(
			@PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC)
			Pageable pageable,
			@JsonView(UserEntryView.FilterUser.class) 
			UserDto userDto) {
		
		var userDtoPage = userService.findAllByEmailOrStatusOrType(pageable, userDto);
		return status(200).body(userDtoPage);
	}

	@DeleteMapping(path = "{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
		
		userService.deleteById(userId);		
		return ok().body("User deleted successfully");
	}

	@JsonView(UserReturnView.Default.class)
	@PutMapping(path = "{userId}")
	public UserDto updateUser(@PathVariable UUID userId,
			@RequestBody @JsonView(UserEntryView.UpdateUser.class) 
			UserDto userDto) {
		
		return userService.updateUser(userId, userDto);
	}

	@PutMapping(path = "{userId}/password")
	public ResponseEntity<String> updatePassword(@PathVariable UUID userId,
			@RequestBody
			@Validated(UserEntryView.UpdatePassword.class)
			@JsonView(UserEntryView.UpdatePassword.class) 
			UserDto userDto) {
		
		userService.updatePassword(userId, userDto);
		return ok().body("Password updated successfully.");
	}

	@PutMapping(path = "{userId}/image")
	@JsonView(UserReturnView.Default.class)
	public UserDto updateImage(@PathVariable UUID userId,
			@RequestBody 
			@JsonView(UserEntryView.UpdateImage.class) 
			@Validated(UserEntryView.UpdateImage.class) 
			UserDto userDto) {
		
		return userService.updateImage(userId, userDto);
	}
}
