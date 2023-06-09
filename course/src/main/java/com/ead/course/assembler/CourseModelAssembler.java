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
	/**
	 * 
	 * @param modificationsNotAllowed
	 * Objeto que veio do banco de dados e que possui valores que não podem ser modificados. 
	 * @param courseModifications
	 * Objeto que possui as modificações desejadas, esse objeto não pode ter modificações nos seguintes atributos:
	 * courseId, lastUpdateDate, creationDate, modules
	 */
	public void copyPropertiesCannotBeModified(CourseEntity modificationsNotAllowed, CourseEntity courseModifications) {
		courseModifications.setCourseId(modificationsNotAllowed.getCourseId());
		courseModifications.setLastUpdateDate(modificationsNotAllowed.getLastUpdateDate());
		courseModifications.setCreationDate(modificationsNotAllowed.getCreationDate());
		courseModifications.setModules(modificationsNotAllowed.getModules());		;
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
