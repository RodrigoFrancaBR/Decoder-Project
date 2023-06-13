package com.ead.course.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ead.course.assembler.LessonEntityAssembler;
import com.ead.course.assembler.LessonModelAssembler;
import com.ead.course.model.LessonModel;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LessonFacade {

	private final ModuleService moduleService;
	private final LessonService lessonService;

	private final LessonModelAssembler modelAssembler;
	private final LessonEntityAssembler entityAssembler;

	public LessonModel saveLesson(UUID moduleId, LessonModel lessonModel) {
		var moduleEntity = moduleService.findByModuleId(moduleId);
		var lessonEntity = entityAssembler.toEntity(lessonModel);
		lessonEntity.setModule(moduleEntity);
		var lessonSave = lessonService.save(lessonEntity);
		var lessonModelSave = modelAssembler.toModel(lessonSave);
		return lessonModelSave;
	}

}
