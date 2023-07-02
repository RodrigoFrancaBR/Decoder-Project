package com.ead.course.assembler;

import com.ead.course.controllers.LessonController;
import com.ead.course.controllers.links.LinksLessons;
import com.ead.course.entity.LessonEntity;
import com.ead.course.model.LessonModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class LessonModelAssembler extends RepresentationModelAssemblerSupport<LessonEntity, LessonModel> {

    @Autowired
    private LinksLessons links;

    public LessonModelAssembler() {
        super(LessonController.class, LessonModel.class);
    }

    @Override
    public abstract LessonModel toModel(LessonEntity lessonEntity);

    @AfterMapping
    protected void addLinks(@MappingTarget LessonModel lessonModel, LessonEntity lessonEntity) {
        var lessonByModuleAndLessonId = links.linkToFindByModuleAndLessonId(lessonEntity.getModule().getModuleId(), lessonEntity.getLessonId());
        lessonModel.add(Link.of(lessonByModuleAndLessonId, "Lesson ByModuleAndLessonId"));

        // posso fazer para ter 2 links um para obter modulo ByCourseAndModuleId e todos os modulos.
        // moduleModel.add(Link.of(moduleByCourseAndModuleId, "Module ByCourseAndModuleId"))
        // .add(Link.of(allModules, "Modules AllModules"));
    }

}
