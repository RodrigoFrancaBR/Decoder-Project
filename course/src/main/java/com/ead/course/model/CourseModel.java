package com.ead.course.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.ead.course.entity.ModuleEntity;
import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "Courses")
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseModel extends RepresentationModel<CourseModel> {

	private UUID courseId;

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	private String imageUrl;

	private LocalDateTime creationDate;

	private LocalDateTime lastUpdateDate;

	@NotNull
	private CourseStatus courseStatus;

	@NotNull
	private CourseLevel courseLevel;

	@NotNull
	private UUID userInstructor;

	private Set<ModuleEntity> modules;

}
