package com.ead.course.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.ead.course.controllers.views.CourseEntryView;
import com.ead.course.controllers.views.CourseReturnView;
import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "Courses")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseModel extends RepresentationModel<CourseModel> {

	// @JsonView({ CourseReturnView.Default.class})
	private UUID courseId;
	
	@JsonView({ CourseEntryView.RegisterCourse.class,
				CourseEntryView.UpdateCourse.class,
				CourseReturnView.Default.class } )
	@NotBlank(groups = {CourseEntryView.RegisterCourse.class,
						CourseEntryView.UpdateCourse.class})
	private String name;
	
	@JsonView({ CourseEntryView.RegisterCourse.class,
				CourseEntryView.UpdateCourse.class,
				CourseReturnView.Default.class } )
	@NotBlank(groups = {CourseEntryView.RegisterCourse.class,
			  			CourseEntryView.UpdateCourse.class})
	private String description;

	@JsonView({ CourseEntryView.RegisterCourse.class,
				CourseEntryView.UpdateCourse.class,
				CourseReturnView.Default.class } )
	private String imageUrl;
	
	@JsonView({ CourseReturnView.Default.class } )
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime creationDate;
		
	@JsonView({ CourseReturnView.Default.class } )
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime lastUpdateDate;
	
	@JsonView({ CourseEntryView.RegisterCourse.class,
				CourseEntryView.UpdateCourse.class,
				CourseReturnView.Default.class})
	@NotNull(groups = { CourseEntryView.RegisterCourse.class,
					    CourseEntryView.UpdateCourse.class} )
	private CourseStatus courseStatus;
		
	@JsonView({ CourseEntryView.RegisterCourse.class,
				CourseEntryView.UpdateCourse.class,
				CourseReturnView.Default.class})
	@NotNull(groups = { CourseEntryView.RegisterCourse.class,
						CourseEntryView.UpdateCourse.class})
	private UUID userInstructor;
	
	@JsonView({ CourseEntryView.RegisterCourse.class,
				CourseEntryView.UpdateCourse.class,
				CourseReturnView.Default.class } )
	@NotNull(groups = {CourseEntryView.RegisterCourse.class,
					   CourseEntryView.UpdateCourse.class} )
	private CourseLevel courseLevel;
	
	/* 
	@JsonView({ CourseEntryView.RegisterCourse.class })
	private Set<ModuleModel> modules;*/

}
