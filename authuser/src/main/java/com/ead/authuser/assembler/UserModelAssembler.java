package com.ead.authuser.assembler;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.ead.authuser.controllers.UserController;
import com.ead.authuser.controllers.util.LinksFactory;
import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.model.UserModel;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class UserModelAssembler extends RepresentationModelAssemblerSupport<UserEntity, UserModel> {

	@Autowired
	private LinksFactory factory;

	public UserModelAssembler() {
		super(UserController.class, UserModel.class);
	}

	@Override
	@Mapping(target = "nickName", source = "user.userName")
	public abstract UserModel toModel(UserEntity user);

	@AfterMapping
	protected void addLinks(@MappingTarget UserModel userModel, UserEntity user) {
		var userWithSelfAndRelationLinks = createModelWithId(user.getUserId(), user)
				.add(factory.linkToUsers());

		userModel.add(userWithSelfAndRelationLinks.getLinks());

	}
}
