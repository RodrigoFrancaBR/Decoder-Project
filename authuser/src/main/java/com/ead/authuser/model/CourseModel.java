package com.ead.authuser.model;

import com.ead.authuser.enums.CourseLevel;
import com.ead.authuser.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

// @Relation(collectionRelation = "Courses")
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseModel{

    // @JsonView({CourseReturnView.Default.class})
    private UUID courseId;

//    @JsonView({CourseEntryView.RegisterCourse.class,
//        CourseEntryView.UpdateCourse.class,
//        CourseEntryView.FilterByLevelAndStatusAndName.class,
//        CourseReturnView.Default.class,
//        CourseEntryView.FilterCourse.class})
//    @NotBlank(groups = {CourseEntryView.RegisterCourse.class,
//        CourseEntryView.UpdateCourse.class})
    private String name;

//    @JsonView({CourseEntryView.RegisterCourse.class,
//        CourseEntryView.UpdateCourse.class,
//        CourseReturnView.Default.class})
//    @NotBlank(groups = {CourseEntryView.RegisterCourse.class,
//        CourseEntryView.UpdateCourse.class})
    private String description;

//    @JsonView({CourseEntryView.RegisterCourse.class,
//        CourseEntryView.UpdateCourse.class,
//        CourseReturnView.Default.class})
    private String imageUrl;

//    @JsonView({CourseEntryView.RegisterCourse.class,
//        CourseEntryView.UpdateCourse.class,
//        CourseEntryView.FilterByLevelAndStatusAndName.class,
//        CourseReturnView.Default.class,
//        CourseEntryView.FilterCourse.class})
//    @NotNull(groups = {CourseEntryView.RegisterCourse.class,
//        CourseEntryView.UpdateCourse.class})
    private CourseStatus courseStatus;

//    @JsonView({CourseEntryView.RegisterCourse.class,
//        CourseEntryView.UpdateCourse.class,
//        CourseReturnView.Default.class})
//    @NotNull(groups = {CourseEntryView.RegisterCourse.class,
//        CourseEntryView.UpdateCourse.class})
    private UUID userInstructor;

//    @JsonView({CourseEntryView.RegisterCourse.class,
//        CourseEntryView.UpdateCourse.class,
//        CourseEntryView.FilterByLevelAndStatusAndName.class,
//        CourseReturnView.Default.class,
//        CourseEntryView.FilterCourse.class})
//    @NotNull(groups = {CourseEntryView.RegisterCourse.class,
//        CourseEntryView.UpdateCourse.class})
    private CourseLevel courseLevel;
}