package com.ead.course.services;

import com.ead.course.entity.CourseEntity;
import com.ead.course.model.CourseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.UUID;

public interface CourseService {

    public Page<CourseEntity> findAll(Pageable pageable);

    public CourseEntity findByCourseId(UUID courseId);

    public CourseEntity save(CourseEntity courseEntity);

    public void deleteById(UUID courseId);

    public CourseEntity findCourseEntity(UUID courseId);

    public CourseModel save(CourseModel courseModel);

    PagedModel<CourseModel> findAllByLevelAndStatusAndName(Pageable pageable, CourseModel courseModel);
}
