package com.ead.authuser.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;

import com.ead.authuser.dto.UserModel;

public interface UserService {
	Page<CollectionModel<UserModel>> findAll(Pageable pageable);

	UserModel getOneUser(UUID userId);

	void deleteById(UUID userId);

	UserModel save(UserModel userDto);

	UserModel updateUser(UUID userId, UserModel userDto);

	void updatePassword(UUID userId, UserModel userDto);

	UserModel updateImage(UUID userId, UserModel userDto);

	Page<UserModel> findAllByEmailAndStatusAndType(Pageable pageable, UserModel userDto);

	Page<UserModel> findAllByEmailOrStatusOrType(Pageable pageable, UserModel userDto);
}