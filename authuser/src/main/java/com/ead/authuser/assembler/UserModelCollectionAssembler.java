package com.ead.authuser.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.ead.authuser.controllers.UserController;
import com.ead.authuser.dto.UserModel;
import com.ead.authuser.entity.UserEntity;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public class UserModelCollectionAssembler extends RepresentationModelAssemblerSupport<UserEntity, UserModel> {
	
	public UserModelCollectionAssembler() {
		super(UserController.class, UserModel.class);
	}

	@Override
	public UserModel toModel(UserEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public CollectionModel<UserModel> toCollectionModel(Iterable<? extends UserEntity> entities) {		
		return super.toCollectionModel(entities)
				.add(linkTo(UserController.class).withSelfRel());
	}


}
