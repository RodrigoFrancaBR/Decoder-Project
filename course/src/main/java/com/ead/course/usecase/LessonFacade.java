package com.ead.course.usecase;

import com.ead.course.assembler.LessonEntityAssembler;
import com.ead.course.assembler.LessonModelAssembler;
import com.ead.course.entity.LessonEntity;
import com.ead.course.model.LessonModel;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LessonFacade {

	private final ModuleService moduleService;
	private final LessonService lessonService;

	private final LessonModelAssembler modelAssembler;
	private final LessonEntityAssembler entityAssembler;

	private final PagedResourcesAssembler<LessonEntity> assembler;

	public LessonModel saveLesson(UUID moduleId, LessonModel lessonModel) {
		var moduleEntity = moduleService.findByModuleId(moduleId);
		var lessonEntity = entityAssembler.toEntity(lessonModel);
		lessonEntity.setModule(moduleEntity);
		var lessonSave = lessonService.save(lessonEntity);
		var lessonModelSave = modelAssembler.toModel(lessonSave);
		return lessonModelSave;
	}

	public void deleteLesson(UUID moduleId, UUID lessonId) {
		var lessonEntity = lessonService.findByModuleAndLessonId(moduleId, lessonId);
		lessonService.delete(lessonEntity);
	}

	public LessonModel updateLesson(UUID moduleId, UUID lessonId, LessonModel lessonModel) {
		var lessonEntity = lessonService.findByModuleAndLessonId(moduleId, lessonId);
		entityAssembler.copyNonNullProperties(lessonModel, lessonEntity);
		return modelAssembler.toModel(lessonService.save(lessonEntity));
	}

	public CollectionModel<LessonModel> findAllByModuleId(UUID moduleId) {
		return modelAssembler.toCollectionModel(lessonService.findAllByModuleId(moduleId));
	}

	public LessonModel findLesson(UUID moduleId, UUID lessonId) {
		return modelAssembler.toModel(lessonService.findByModuleAndLessonId(moduleId, lessonId));
	}

}
