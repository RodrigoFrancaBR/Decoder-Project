package com.ead.course.model;

import com.ead.course.controllers.views.LessonEntryView;
import com.ead.course.controllers.views.LessonReturnView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "Lessons")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonModel extends RepresentationModel<LessonModel> {

	@JsonView({ LessonReturnView.Default.class })
	private UUID lessonId;

	@JsonView({ LessonReturnView.Default.class,
				LessonEntryView.RegisterLesson.class,
				LessonEntryView.UpdateLesson.class})
	@NotBlank(groups = LessonEntryView.RegisterLesson.class)
	private String title;
	
	@JsonView({ LessonReturnView.Default.class,
				LessonEntryView.RegisterLesson.class,
				LessonEntryView.UpdateLesson.class})
	private String description;
	
	@JsonView({ LessonReturnView.Default.class,
				LessonEntryView.RegisterLesson.class,
				LessonEntryView.UpdateLesson.class})
	@NotBlank(groups = LessonEntryView.RegisterLesson.class)
	private String videoUrl;
	
	@JsonView({ LessonReturnView.Default.class })
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime creationDate;

	/*	
	@JsonView({ LessonReturnView.Default.class })
	private ModuleEntity module;*/
	
}
