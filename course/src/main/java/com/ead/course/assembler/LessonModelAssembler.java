package com.ead.course.assembler;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.ead.course.controllers.LessonController;
import com.ead.course.controllers.util.LinksFactory;
import com.ead.course.entity.LessonEntity;
import com.ead.course.model.LessonModel;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class LessonModelAssembler extends RepresentationModelAssemblerSupport<LessonEntity, LessonModel> {

	@Autowired
	private LinksFactory factory;

	public LessonModelAssembler() {
		super(LessonController.class, LessonModel.class);
	}

	@Override
	public abstract LessonModel toModel(LessonEntity lessonEntity);

	@AfterMapping
	protected void addLinks(@MappingTarget LessonModel lessonModel, LessonEntity lessonEntity) {
		var userWithSelfAndRelationLinks = createModelWithId(lessonEntity.getLessonId(), lessonEntity)
				.add(factory.linkToCourses());
		lessonModel.add(userWithSelfAndRelationLinks.getLinks());
	}

}
