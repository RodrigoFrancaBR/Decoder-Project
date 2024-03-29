package com.ead.authuser.model;

import com.ead.authuser.controllers.views.UserEntryView;
import com.ead.authuser.controllers.views.UserReturnView;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.validation.EmailAlreadyExistConstraint;
import com.ead.authuser.validation.NickNameConstraint;
import com.ead.authuser.validation.UserNameAlreadyExistConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "Users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel extends RepresentationModel<UserModel> {

    @JsonView({UserReturnView.Default.class})
    private UUID userId;

    @Size(min = 4, max = 50, groups = UserEntryView.RegisterUser.class)
    @NotBlank(groups = UserEntryView.RegisterUser.class)
    @JsonView({UserEntryView.RegisterUser.class, UserReturnView.Default.class})
    @NickNameConstraint(groups = UserEntryView.RegisterUser.class)
    @UserNameAlreadyExistConstraint(groups = UserEntryView.RegisterUser.class)
    private String nickName;

    @Size(min = 4, max = 50, groups = {UserEntryView.RegisterUser.class})
    @Email(groups = {UserEntryView.RegisterUser.class})
    @NotBlank(groups = UserEntryView.RegisterUser.class)
    @EmailAlreadyExistConstraint(groups = UserEntryView.RegisterUser.class)
    @JsonView({UserEntryView.RegisterUser.class, UserReturnView.Default.class, UserEntryView.FilterUser.class})
    private String email;

    @Size(min = 6, max = 20, groups = {UserEntryView.RegisterUser.class, UserEntryView.UpdatePassword.class})
    @NotBlank(groups = {UserEntryView.RegisterUser.class, UserEntryView.UpdatePassword.class})
    @JsonView({UserEntryView.RegisterUser.class, UserEntryView.UpdatePassword.class})
    private String password;

    @Size(min = 6, max = 20, groups = UserEntryView.UpdatePassword.class)
    @NotBlank(groups = UserEntryView.UpdatePassword.class)
    @JsonView({UserEntryView.UpdatePassword.class})
    private String oldPassword;

    @JsonView({UserEntryView.RegisterUser.class, UserEntryView.UpdateUser.class, UserReturnView.Default.class, UserEntryView.FilterUser.class})
    private String fullName;

    @JsonView({UserReturnView.Default.class, UserEntryView.FilterUser.class})
    private UserStatus userStatus;

    @JsonView({UserReturnView.Default.class, UserEntryView.FilterUser.class})
    private UserType userType;

    @JsonView({UserEntryView.RegisterUser.class, UserEntryView.UpdateUser.class, UserReturnView.Default.class})
    private String phoneNumber;

    @JsonView({UserEntryView.RegisterUser.class, UserEntryView.UpdateUser.class, UserReturnView.Default.class})
    private String cpf;

    @NotBlank(groups = UserEntryView.UpdateImage.class)
    @JsonView({UserEntryView.UpdateImage.class, UserReturnView.Default.class})
    private String imageUrl;

    @JsonView({UserReturnView.Default.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    @JsonView({UserReturnView.Default.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastUpdateDate;
    @JsonView({UserEntryView.FilterUser.class, UserReturnView.Default.class})
    private UUID courseId;
}