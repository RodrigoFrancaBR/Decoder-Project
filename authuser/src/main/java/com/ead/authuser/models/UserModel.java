package com.ead.authuser.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_USERS")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID userId;

	@Column(nullable = false, unique = true, length = 50)
	private String userName;

	@Column(nullable = false, unique = true, length = 50)
	private String email;

	@Column(nullable = false, length = 255)
	@JsonIgnore
	private String password;

	@Column(nullable = false, length = 150)
	private String fullName;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@Column(length = 20)
	private String phoneNumber;

	@Column(length = 20)
	private String cpf;

	@Column
	private String imageUrl;

	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime creationDate;

	@Column(nullable = false)
	@UpdateTimestamp
	private LocalDateTime lastUpdateDate;
}
