package com.ead.authuser.assembler;

import static org.springframework.hateoas.IanaLinkRelations.COLLECTION;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.UUID;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.ead.authuser.controllers.UserController;
import com.ead.authuser.dto.UserModel;
import com.ead.authuser.entity.UserEntity;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class UserModelAssembler extends RepresentationModelAssemblerSupport<UserEntity, UserModel> {

	public UserModelAssembler() {
		super(UserController.class, UserModel.class);
	}

	@Override
	@Mapping(target = "nickName", source = "user.userName")	
	public abstract UserModel toModel(UserEntity user);
	
	
	@Override
	public CollectionModel<UserModel> toCollectionModel(Iterable<? extends UserEntity> entities) {		
		return super.toCollectionModel(entities)
				.add(linkTo(UserController.class).withSelfRel());
	}

	  @AfterMapping	
	protected void addLinks(@MappingTarget UserModel userModel, UserEntity user) {		
		userModel.add(getLinkwithSelfRelation(userModel.getUserId()))
		.add(getLinkWithRelation());
	}
	
	public Link getLinkwithSelfRelation(UUID userId) {
		return linkTo(methodOn(UserController.class).getOneUser(userId)).withSelfRel();
	}
	
	public Link getLinkWithRelation() {
		return linkTo(methodOn(UserController.class).findAll( PageRequest.of(0, 10)))
				.withRel(COLLECTION);
	}
}
