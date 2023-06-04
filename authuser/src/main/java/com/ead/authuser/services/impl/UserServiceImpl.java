package com.ead.authuser.services.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.ead.authuser.assembler.UserModelAssembler;
import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.exceptions.UserConflictException;
import com.ead.authuser.exceptions.UserNotFoundException;
import com.ead.authuser.mapper.UserMapper;
import com.ead.authuser.mapper.UserMapperRegister;
import com.ead.authuser.model.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import com.ead.authuser.specification.UserWithEmailSpec;
import com.ead.authuser.specification.UserWithStatusSpec;
import com.ead.authuser.specification.UserWithTypeSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final UserMapperRegister userMapperRegister;
	private final UserRepository userRepository;
	private final UserModelAssembler userModelAssembler;
	private final PagedResourcesAssembler<UserEntity> pagedResourcesAssembler;
	
	@Override
	public PagedModel<UserModel> findAll(Pageable pageable) {
		Page<UserEntity> pageUserEntity = userRepository.findAll(pageable);
		return pagedResourcesAssembler.toModel(pageUserEntity, userModelAssembler);
	}
	
	@Override
	public UserModel findUser(UUID userId) {
		return userModelAssembler.toModel(findUserIfExist(userId));		
	}

	@Override
	public void deleteById(UUID userId) {
		userRepository.deleteById(findUserIfExist(userId).getUserId());
	}

	@Override
	public UserModel updateUser(UUID userId, UserModel userModel) {
		var user = findUserIfExist(userId);
		user.setFullName(userModel.getFullName());
		user.setPhoneNumber(userModel.getPhoneNumber());
		user.setCpf(userModel.getCpf());
		return userMapper.toDto(userRepository.save(user));
	}

	@Override
	public void updatePassword(UUID userId, UserModel userDto) {
		var userModel = findUserIfExist(userId);

		if (!userDto.getOldPassword().equals(userModel.getPassword())) {
			throw new UserConflictException("Error: Mismatched old password");
		}

		userModel.setPassword(userDto.getPassword());
		userRepository.save(userModel);
	}

	@Override
	public UserModel updateImage(UUID userId, UserModel userDto) {
		var userModel = findUserIfExist(userId);
		userModel.setImageUrl(userDto.getImageUrl());
		return userMapper.toDto(userRepository.save(userModel));
	}



	@Override
	public Page<UserModel> findAllByEmailAndStatusAndType(Pageable pageable, UserModel userDto) {
		var userModelPage = userRepository.findAll(new UserWithEmailSpec(userDto.getEmail())
				.and(new UserWithStatusSpec(userDto.getUserStatus())).and(new UserWithTypeSpec(userDto.getUserType())),
				pageable);

		return userModelPage.map(userMapper::toDto);
	}

	@Override
	public Page<UserModel> findAllByEmailOrStatusOrType(Pageable pageable, UserModel userDto) {
		var userModelPage = userRepository.findAll(new UserWithEmailSpec(userDto.getEmail())
				.or(new UserWithStatusSpec(userDto.getUserStatus())).or(new UserWithTypeSpec(userDto.getUserType())),
				pageable);

		return userModelPage.map(userMapper::toDto);
	}

	@Override
	public UserModel save(UserModel userModel) {
		UserEntity userEntity = userMapperRegister.toEntity(userModel);
		return userMapper.toDto(userRepository.save(userEntity));
	}

	private UserEntity findUserIfExist(UUID userId) {
		return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
	}

	

}
