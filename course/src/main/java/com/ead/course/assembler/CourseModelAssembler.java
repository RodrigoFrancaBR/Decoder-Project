package com.ead.course.assembler;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.ead.course.controllers.util.LinksFactory;
import com.ead.course.controllers.CourseController;
import com.ead.course.entity.CourseEntity;
import com.ead.course.model.CourseModel;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class CourseModelAssembler extends RepresentationModelAssemblerSupport<CourseEntity, CourseModel> {
	@Autowired
	private LinksFactory factory;
	
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
	
	@Override	
	public abstract CourseModel toModel(CourseEntity courseEntity);
	
	@AfterMapping
	protected void addLinks(@MappingTarget CourseModel courseModel, CourseEntity courseEntity) {
		var userWithSelfAndRelationLinks = createModelWithId(courseEntity.getCourseId(), courseEntity)
				.add(factory.linkToCourses());

		courseModel.add(userWithSelfAndRelationLinks.getLinks());

	}

}
