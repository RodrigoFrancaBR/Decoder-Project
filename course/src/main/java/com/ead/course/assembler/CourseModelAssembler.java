package com.ead.course.assembler;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.ead.course.controllers.CourseController;
import com.ead.course.entity.CourseEntity;
import com.ead.course.model.CourseModel;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class CourseModelAssembler extends RepresentationModelAssemblerSupport<CourseEntity, CourseModel> {

	public CourseModelAssembler() {
		super(CourseController.class, CourseModel.class);
	}

	public abstract CourseEntity toEntity(CourseModel courseModel);

	public void copyPropertiesCannotBeModified(CourseEntity courseEntity, CourseEntity entityMapped) {
		entityMapped.setCourseId(courseEntity.getCourseId());
		entityMapped.setLastUpdateDate(courseEntity.getLastUpdateDate());
		entityMapped.setCreationDate(courseEntity.getCreationDate());
		entityMapped.setModules(courseEntity.getModules());		;
	}

}
