package com.ead.course.assembler;

import com.ead.course.entity.CourseEntity;
import com.ead.course.model.CourseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseEntityAssembler extends EntityAssembler<CourseModel, CourseEntity> {

	CourseEntity toEntity(CourseModel model);
}
