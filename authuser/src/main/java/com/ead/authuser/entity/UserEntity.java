package com.ead.authuser.entity;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_USERS")
public class UserEntity {

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

	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<UserCourseEntity> userCourses;
}