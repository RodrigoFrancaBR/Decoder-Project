package com.ead.course.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.ead.course.controllers.views.ModuleEntryView;
import com.ead.course.controllers.views.ModuleReturnView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
	private UUID moduleId;
	
	@JsonView({ModuleEntryView.RegisterModule.class,
				ModuleEntryView.UpdateModule.class,
			   ModuleReturnView.Default.class } )
	@NotBlank(groups = {ModuleEntryView.RegisterModule.class})
	private String title;
	
	@JsonView({ModuleEntryView.RegisterModule.class,
				ModuleEntryView.UpdateModule.class,		
			  ModuleReturnView.Default.class } )
	@NotBlank(groups = {ModuleEntryView.RegisterModule.class})
    private String description;
	
	@JsonView({ ModuleReturnView.Default.class })
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime creationDate;
	
/*
	@JsonView({ ModuleReturnView.Default.class })
	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private CourseModel course;

	@JsonView({ ModuleReturnView.Default.class })
	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Set<LessonModel> lessons;*/
	
}
