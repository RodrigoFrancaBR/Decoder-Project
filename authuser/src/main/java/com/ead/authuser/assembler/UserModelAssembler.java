package com.ead.authuser.assembler;

import static org.springframework.hateoas.IanaLinkRelations.COLLECTION;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
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

	@AfterMapping
	protected void addLinks(@MappingTarget UserModel userModel, UserEntity user) {
		var userWithLinks = createModelWithId(user.getUserId(), user).add(getLinkWithRelation());
		userModel.add(userWithLinks.getLinks());
	}

	public Link getLinkWithRelation() {
		var uriTemplate = UriTemplate.of(getUrl(), buildTemplateVariables());
		return Link.of(uriTemplate, COLLECTION);
	}
	
	public String getUrl() {
		return linkTo(UserController.class).toUri().toString();
	}

	public TemplateVariables buildTemplateVariables() {
		return new TemplateVariables(new TemplateVariable("page", VariableType.REQUEST_PARAM),
				new TemplateVariable("size", VariableType.REQUEST_PARAM),
				new TemplateVariable("sort", VariableType.REQUEST_PARAM),
				new TemplateVariable("direction", VariableType.REQUEST_PARAM));
	}
}
