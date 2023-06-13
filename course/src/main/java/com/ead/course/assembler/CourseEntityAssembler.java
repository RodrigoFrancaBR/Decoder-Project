package com.ead.course.assembler;

import org.mapstruct.Mapper;

import com.ead.course.entity.CourseEntity;
import com.ead.course.model.CourseModel;

@Mapper(componentModel = "spring")
public interface CourseEntityAssembler extends EntityAssembler<CourseModel, CourseEntity> {

	CourseEntity toEntity(CourseModel model);
}
