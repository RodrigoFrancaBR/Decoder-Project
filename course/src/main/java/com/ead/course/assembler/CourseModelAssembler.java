package com.ead.course.assembler;

import com.ead.course.controllers.CourseController;
import com.ead.course.controllers.links.LinksCourse;
import com.ead.course.entity.CourseEntity;
import com.ead.course.model.CourseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class CourseModelAssembler extends RepresentationModelAssemblerSupport<CourseEntity, CourseModel> {

    @Autowired
    private LinksCourse factory;

    public CourseModelAssembler() {
        super(CourseController.class, CourseModel.class);
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
