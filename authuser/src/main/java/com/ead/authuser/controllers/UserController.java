package com.ead.authuser.controllers;

import com.ead.authuser.controllers.views.UserEntryView;
import com.ead.authuser.controllers.views.UserReturnView;
import com.ead.authuser.model.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@JsonView(UserReturnView.Default.class)
	@GetMapping
	public  PagedModel<UserModel> findAll(
			@PageableDefault(page = 0, size = 10, 
			sort = "userId", direction = Sort.Direction.ASC)
			Pageable pageable) {
		return userService.findAll(pageable);
	}

	@JsonView(UserReturnView.Default.class)
	@GetMapping(path = "/{userId}")
	public UserModel getOneUser(@PathVariable UUID userId) {
		return userService.findUser(userId);
	}

	@JsonView(UserReturnView.Default.class)
	@GetMapping(path = "byEmailAndStatusAndType")
	public ResponseEntity<PagedModel<UserModel>> findAllByEmailAndStatusAndType(
			@PageableDefault(page = 0, size = 10, sort = "userId",
			direction = Sort.Direction.ASC) 
			Pageable pageable,
			@JsonView(UserEntryView.FilterUser.class)
			UserModel userModel) {

		var pagedModel = userService.findAllByEmailAndStatusAndType(pageable, userModel);
		return status(200).body(pagedModel);
	}

	@JsonView(UserReturnView.Default.class)
	@GetMapping(path = "byEmailOrStatusOrType")
	public ResponseEntity<PagedModel<UserModel>> findAllByEmailOrStatusOrType(
			@PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable,
			@JsonView(UserEntryView.FilterUser.class) UserModel userModel) {

		var pagedModel = userService.findAllByEmailOrStatusOrType(pageable, userModel);
		return status(200).body(pagedModel);
	}

	@DeleteMapping(path = "{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {

		userService.deleteById(userId);
		return ok().body("User deleted successfully");
	}

	@JsonView(UserReturnView.Default.class)
	@PutMapping(path = "{userId}")
	public UserModel updateUser(
			@PathVariable UUID userId,
			@RequestBody
			@JsonView(UserEntryView.UpdateUser.class) 
			UserModel userDto) {

		return userService.updateUser(userId, userDto);
	}

	@PutMapping(path = "{userId}/password")
	public ResponseEntity<String> updatePassword(@PathVariable UUID userId,
			@RequestBody @Validated(UserEntryView.UpdatePassword.class) @JsonView(UserEntryView.UpdatePassword.class) UserModel userDto) {

		userService.updatePassword(userId, userDto);
		return ok().body("Password updated successfully.");
	}

	@PutMapping(path = "{userId}/image")
	@JsonView(UserReturnView.Default.class)
	public UserModel updateImage(@PathVariable UUID userId,
			@RequestBody @JsonView(UserEntryView.UpdateImage.class) @Validated(UserEntryView.UpdateImage.class) UserModel userDto) {

		return userService.updateImage(userId, userDto);
	}
}
