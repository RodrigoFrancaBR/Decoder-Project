package com.ead.authuser.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.ead.authuser.dto.view.UserEntryView;
import com.ead.authuser.dto.view.UserReturnView;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.validation.EmailAlreadyExistConstraint;
import com.ead.authuser.validation.NickNameConstraint;
import com.ead.authuser.validation.UserNameAlreadyExistConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends RepresentationModel<UserDto> implements UserLinkBuilder<UserDto>{

	@JsonView({ UserReturnView.Default.class })
	private UUID userId;

	@Size(min = 4, max = 50, groups = UserEntryView.RegisterUser.class)
	@NotBlank(groups = UserEntryView.RegisterUser.class)
	@JsonView({ UserEntryView.RegisterUser.class, UserReturnView.Default.class })
	@NickNameConstraint(groups = UserEntryView.RegisterUser.class)
	@UserNameAlreadyExistConstraint(groups = UserEntryView.RegisterUser.class)
	private String nickName;

	@Size(min = 4, max = 50, groups = { UserEntryView.RegisterUser.class })
	@Email(groups = { UserEntryView.RegisterUser.class })
	@NotBlank(groups = UserEntryView.RegisterUser.class)
	@EmailAlreadyExistConstraint(groups = UserEntryView.RegisterUser.class)
	@JsonView({ UserEntryView.RegisterUser.class, UserReturnView.Default.class, UserEntryView.FilterUser.class })
	private String email;

	@Size(min = 6, max = 20, groups = { UserEntryView.RegisterUser.class, UserEntryView.UpdatePassword.class })
	@NotBlank(groups = { UserEntryView.RegisterUser.class, UserEntryView.UpdatePassword.class })
	@JsonView({ UserEntryView.RegisterUser.class, UserEntryView.UpdatePassword.class })
	private String password;

	@Size(min = 6, max = 20, groups = UserEntryView.UpdatePassword.class)
	@NotBlank(groups = UserEntryView.UpdatePassword.class)
	@JsonView({ UserEntryView.UpdatePassword.class })
	private String oldPassword;

	@JsonView({ UserEntryView.RegisterUser.class, UserEntryView.UpdateUser.class, UserReturnView.Default.class })
	private String fullName;

	@JsonView({ UserReturnView.Default.class, UserEntryView.FilterUser.class })
	private UserStatus userStatus;

	@JsonView({ UserReturnView.Default.class, UserEntryView.FilterUser.class })
	private UserType userType;

	@JsonView({ UserEntryView.RegisterUser.class, UserEntryView.UpdateUser.class, UserReturnView.Default.class })
	private String phoneNumber;

	@JsonView({ UserEntryView.RegisterUser.class, UserEntryView.UpdateUser.class, UserReturnView.Default.class })
	private String cpf;

	@NotBlank(groups = UserEntryView.UpdateImage.class)
	@JsonView({ UserEntryView.UpdateImage.class, UserReturnView.Default.class })
	private String imageUrl;

	@JsonView({ UserReturnView.Default.class })
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime creationDate;

	@JsonView({ UserReturnView.Default.class })
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime lastUpdateDate;

	public UserDto buildSelfLink(String uriLocation) {
		return this.add(Link.of(uriLocation));
	}

	public UserDto buildCollectionLink(String uriLocation) {
		return this.add(Link.of(uriLocation));
	}

	public UserDto buildSelfAndCollectionLink(String currentRequestUri, String defaultUrl) {
		return this.add(Link.of(currentRequestUri)).add(Link.of(defaultUrl, IanaLinkRelations.COLLECTION));
	}

	public UserDto buildSelfAndCollectionLink(String buildUriLocation) {
		return this.add(Link.of(buildUriLocation));
	}

	public UserDto buildLinkWithSelfAndRelation(Class<?> controller, UUID userId) {
		Link link = getLinkWithSelfRel(controller, userId);
		return this.add(link)
				.add(getLinkWithRel(controller));
	}
	
	/*public UserDto buildLinkWithSelfAndRelation(Link linkWithRel, Link linkWithSelfRel) {
		return null;
	}*/

	private Link getLinkWithRel(Class<?> controller) {
		return WebMvcLinkBuilder
				.linkTo(controller)				
				.withRel(IanaLinkRelations.COLLECTION);				
	}

	private Link getLinkWithSelfRel(Class<?> controller, UUID userId) {
		return WebMvcLinkBuilder
				.linkTo(controller)
				.slash(userId)
				.withSelfRel();
	}

	@Override
	public UserDto buildLinkWithSelfAndRelation(Link linkWithSelfRelation, Link linkWithRelation) {
		return this.add(linkWithSelfRelation).add(linkWithRelation);
	}

}
