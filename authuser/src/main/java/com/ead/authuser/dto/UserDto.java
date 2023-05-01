package com.ead.authuser.dto;

import com.ead.authuser.dto.view.UserEntryView;
import com.ead.authuser.dto.view.UserReturnView;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class    UserDto {

    @JsonView({UserReturnView.Default.class})
    private UUID userId;

    @Size(min = 4, max = 50, groups = UserEntryView.RegisterUser.class)
    @NotBlank(groups = UserEntryView.RegisterUser.class)
    @JsonView({UserEntryView.RegisterUser.class, UserReturnView.Default.class})
    private String nickName;

    @Size(min = 4, max = 50, groups = UserEntryView.RegisterUser.class)
    @Email(groups = UserEntryView.RegisterUser.class)
    @NotBlank(groups = UserEntryView.RegisterUser.class)
    @JsonView({UserEntryView.RegisterUser.class, UserReturnView.Default.class})
    private String email;

    @Size(min = 6, max = 20, groups = {UserEntryView.RegisterUser.class, UserEntryView.UpdatePassword.class})
    @NotBlank(groups = {UserEntryView.RegisterUser.class, UserEntryView.UpdatePassword.class})
    @JsonView({UserEntryView.RegisterUser.class, UserEntryView.UpdatePassword.class})
    private String password;

    @Size(min = 6, max = 20, groups = UserEntryView.UpdatePassword.class)
    @NotBlank(groups = UserEntryView.UpdatePassword.class)
    @JsonView({UserEntryView.UpdatePassword.class})
    private String oldPassword;

    @JsonView({UserEntryView.RegisterUser.class, UserEntryView.UpdateUser.class, UserReturnView.Default.class})
    private String fullName;

    @JsonView({UserReturnView.Default.class})
    private UserStatus userStatus;

    @JsonView({UserReturnView.Default.class})
    private UserType userType;

    @JsonView({UserEntryView.RegisterUser.class, UserEntryView.UpdateUser.class, UserReturnView.Default.class})
    private String phoneNumber;

    @JsonView({UserEntryView.RegisterUser.class, UserEntryView.UpdateUser.class, UserReturnView.Default.class})
    private String cpf;

    @NotBlank(groups = UserEntryView.UpdateImage.class)
    @JsonView({UserEntryView.UpdateImage.class, UserReturnView.Default.class})
    private String imageUrl;

    @JsonView({UserReturnView.Default.class})
    private LocalDateTime creationDate;

    @JsonView({UserReturnView.Default.class})
    private LocalDateTime lastUpdateDate;
}
