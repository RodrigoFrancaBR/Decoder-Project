package com.ead.course.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.ead.course.controllers.views.ModuleEntryView;
import com.ead.course.controllers.views.ModuleReturnView;
import com.ead.course.entity.CourseEntity;
import com.ead.course.entity.LessonEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "Modules")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleModel extends RepresentationModel<ModuleModel> {
	
	@JsonView({ ModuleReturnView.Default.class})
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private UUID moduleId;
	
	@JsonView({ModuleEntryView.RegisterModule.class,		
			   ModuleReturnView.Default.class } )
	@NotBlank(groups = {ModuleEntryView.RegisterModule.class})
	private String title;
	
	@JsonView({ModuleEntryView.RegisterModule.class,		
			  ModuleReturnView.Default.class } )
	@NotBlank(groups = {ModuleEntryView.RegisterModule.class})
    private String description;
	
	@JsonView({ ModuleReturnView.Default.class })
	private LocalDateTime creationDate;

	@JsonView({ ModuleReturnView.Default.class })
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private CourseEntity course;

	@JsonView({ ModuleReturnView.Default.class })
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Set<LessonEntity> lessons;
	
	
}
