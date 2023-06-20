package com.ead.course.assembler;

import com.ead.course.entity.LessonEntity;
import com.ead.course.model.LessonModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonEntityAssembler extends EntityAssembler<LessonModel, LessonEntity> {

	LessonEntity toEntity(LessonModel model);
}
