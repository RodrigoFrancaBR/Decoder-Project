package com.ead.course.services;

import com.ead.course.entity.LessonEntity;
import com.ead.course.model.LessonModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.UUID;

public interface LessonService {

	void deleteAllByModuleId(UUID moduleId);

	PagedModel<LessonModel> findAllByTitleAndModuleId(String title, UUID moduleId, Pageable pageable);

	LessonEntity save(LessonEntity lessonEntity);

	LessonEntity findByModuleAndLessonId(UUID moduleId, UUID lessonId);

	void delete(LessonEntity lessonEntity);

}
