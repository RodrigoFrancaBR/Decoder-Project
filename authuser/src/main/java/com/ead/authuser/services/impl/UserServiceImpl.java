package com.ead.authuser.services.impl;

import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.ead.authuser.assembler.UserEntityAssembler;
import com.ead.authuser.assembler.UserModelAssembler;
import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.exceptions.UserConflictException;
import com.ead.authuser.exceptions.UserNotFoundException;
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

	private final UserModelAssembler modelAssembler;
	private final UserEntityAssembler entityAssembler;
	private final PagedResourcesAssembler<UserEntity> pagedResourcesAssembler;
	private final UserRepository userRepository;

	@Override
	public UserModel save(UserModel userModel) {
		var userEntity = entityAssembler.toEntity(userModel);
		var entitySave = userRepository.save(userEntity);
		var modelSave = modelAssembler.toModel(entitySave);
		return modelSave;

	}

	@Override
	public PagedModel<UserModel> findAll(Pageable pageable) {
		var pageEntity = userRepository.findAll(pageable);
		return pagedResourcesAssembler.toModel(pageEntity, modelAssembler);
	}

	@Override
	public PagedModel<UserModel> findAllByEmailAndStatusAndType(Pageable pageable, UserModel userDto) {
		var pageEntity = userRepository.findAll(new UserWithEmailSpec(userDto.getEmail())
				.and(new UserWithStatusSpec(userDto.getUserStatus())).and(new UserWithTypeSpec(userDto.getUserType())),
				pageable);
		var pagedModel = pagedResourcesAssembler.toModel(pageEntity, modelAssembler);
		return pagedModel;
	}

	@Override
	public PagedModel<UserModel> findAllByEmailOrStatusOrType(Pageable pageable, UserModel userDto) {
		var pageEntity = userRepository.findAll(new UserWithEmailSpec(userDto.getEmail())
				.or(new UserWithStatusSpec(userDto.getUserStatus())).or(new UserWithTypeSpec(userDto.getUserType())),
				pageable);
		var pagedModel = pagedResourcesAssembler.toModel(pageEntity, modelAssembler);
		return pagedModel;
	}

	@Override
	public UserModel findUser(UUID userId) {
		return modelAssembler.toModel(findUserIfExist(userId));
	}

	@Override
	public UserModel updateUser(UUID userId, UserModel userModel) {
		var userEntity = findUserIfExist(userId);
		entityAssembler.copyNonNullProperties(userModel, userEntity);
		var entitySave = userRepository.save(userEntity);
		var modelSave = modelAssembler.toModel(entitySave);
		return modelSave;
	}

	@Override
	public UserModel updateImage(UUID userId, UserModel userModel) {
		var userEntity = findUserIfExist(userId);
		entityAssembler.copyNonNullProperties(userModel, userEntity);
		var entitySave = userRepository.save(userEntity);
		var modelSave = modelAssembler.toModel(entitySave);
		return modelSave;
	}

	@Override
	public void updatePassword(UUID userId, UserModel userModel) {
		var userEntity = findUserIfExist(userId);

		if (!Objects.equals(userModel.getOldPassword(), userEntity.getPassword())) {
			throw new UserConflictException("Error: Mismatched old password");
		}

		userEntity.setPassword(userModel.getPassword());
		userRepository.save(userEntity);
	}

	@Override
	public void deleteById(UUID userId) {
		userRepository.deleteById(findUserIfExist(userId).getUserId());
	}

	private UserEntity findUserIfExist(UUID userId) {
		return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
	}
}
