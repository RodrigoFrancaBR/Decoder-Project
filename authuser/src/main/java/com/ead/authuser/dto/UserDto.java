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

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @JsonView({UserReturnView.Default.class})
    private UUID userId;

    @JsonView({UserReturnView.Default.class, UserEntryView.RegisterUser.class})
    // trocar userName por nickName só pra fins de teste com o modelmapper
    private String nickName;

    @JsonView({UserReturnView.Default.class, UserEntryView.RegisterUser.class})
    private String email;

    @JsonView({UserEntryView.RegisterUser.class, UserEntryView.UpdatePassword.class})
    private String password;

    @JsonView({UserEntryView.UpdatePassword.class})
    private String oldPassword;

    @JsonView({UserReturnView.Default.class, UserEntryView.RegisterUser.class, UserEntryView.UpdateUser.class})
    private String fullName;

    @JsonView({UserReturnView.Default.class})
    private UserStatus userStatus;

    @JsonView({UserReturnView.Default.class})
    private UserType userType;

    @JsonView({UserReturnView.Default.class, UserEntryView.RegisterUser.class, UserEntryView.UpdateUser.class})
    private String phoneNumber;

    @JsonView({UserReturnView.Default.class, UserEntryView.RegisterUser.class, UserEntryView.UpdateUser.class})
    private String cpf;

    @JsonView({UserReturnView.Default.class, UserEntryView.UpdateImage.class})
    private String imageUrl;

    @JsonView({UserReturnView.Default.class})
    private LocalDateTime creationDate;

    @JsonView({UserReturnView.Default.class})
    private LocalDateTime lastUpdateDate;
}
