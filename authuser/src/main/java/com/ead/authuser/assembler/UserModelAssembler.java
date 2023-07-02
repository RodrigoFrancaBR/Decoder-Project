package com.ead.authuser.assembler;

import com.ead.authuser.controllers.UserController;
import com.ead.authuser.controllers.links.LinksFactory;
import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.model.UserModel;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

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
	protected void addLinks(@MappingTarget UserModel userModel, UserEntity userEntity) {
		var userWithSelfAndRelationLinks = createModelWithId(userEntity.getUserId(), userEntity)
				.add(factory.linkToUsers());

		userModel.add(userWithSelfAndRelationLinks.getLinks());
	}	
}
