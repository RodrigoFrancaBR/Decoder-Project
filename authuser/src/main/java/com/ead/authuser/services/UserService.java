package com.ead.authuser.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ead.authuser.dto.UserDto;

public interface UserService {
	List<UserDto> findAll();

	UserDto getOneUser(UUID userId);

	void deleteById(UUID userId);

	UserDto save(UserDto userDto);

	UserDto updateUser(UUID userId, UserDto userDto);

	void updatePassword(UUID userId, UserDto userDto);

	UserDto updateImage(UUID userId, UserDto userDto);

	Page<UserDto> findAllByEmailAndStatusAndType(Pageable pageable, UserDto userDto);

	Page<UserDto> findAllByEmailOrStatusOrType(Pageable pageable, UserDto userDto);
}