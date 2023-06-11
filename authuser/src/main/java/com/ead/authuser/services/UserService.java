package com.ead.authuser.services;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.ead.authuser.model.UserModel;

public interface UserService {

	PagedModel<UserModel> findAll(Pageable pageable);

	PagedModel<UserModel> findAllByEmailAndStatusAndType(Pageable pageable, UserModel userDto);

	PagedModel<UserModel> findAllByEmailOrStatusOrType(Pageable pageable, UserModel userDto);

	UserModel findUser(UUID userId);

	UserModel updateImage(UUID userId, UserModel userDto);

	UserModel save(UserModel userDto);

	UserModel updateUser(UUID userId, UserModel userDto);

	void updatePassword(UUID userId, UserModel userDto);

	void deleteById(UUID userId);

}