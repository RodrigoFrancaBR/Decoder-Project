package com.ead.course.assembler;

import org.mapstruct.Mapper;

import com.ead.course.entity.LessonEntity;
import com.ead.course.model.LessonModel;

@Mapper(componentModel = "spring")
public interface LessonEntityAssembler extends EntityAssembler<LessonModel, LessonEntity> {

	LessonEntity toEntity(LessonModel model);
}
