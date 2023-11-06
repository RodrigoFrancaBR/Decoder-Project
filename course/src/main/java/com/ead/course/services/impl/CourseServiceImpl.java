package com.ead.course.services.impl;

import com.ead.course.assembler.CourseModelAssembler;
import com.ead.course.entity.CourseEntity;
import com.ead.course.exceptions.CourseNotFoundException;
import com.ead.course.model.CourseModel;
import com.ead.course.repository.CourseRepository;
import com.ead.course.services.CourseService;
import com.ead.course.services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

import static com.ead.course.specifications.CourseSpecificationFactory.byCourseLevel;
import static com.ead.course.specifications.CourseSpecificationFactory.byCourseStatus;
import static com.ead.course.specifications.CourseSpecificationFactory.byName;
import static com.ead.course.specifications.CourseSpecificationFactory.byUserId;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final ModuleService moduleService;
    private final CourseRepository repository;
    private final CourseModelAssembler modelAssembler;
    private final PagedResourcesAssembler<CourseEntity> pagedResourcesAssembler;

    @Override
    public Page<CourseEntity> findAll(Pageable pageable, CourseModel courseModel) {
        return repository.findAll(byUserId(courseModel.getUserId())
                .and(byCourseLevel(courseModel.getCourseLevel()))
                .and(byCourseStatus(courseModel.getCourseStatus()))
                .and(byName(courseModel.getName()))
            , pageable);
    }

    @Override
    public PagedModel<CourseModel> findAllByLevelAndStatusAndName(Pageable pageable, CourseModel courseModel) {
        // verificar se não é melhor fazer findAll(byFindAllByLevelAndStatusAndName) ou seja um unico predicado.
        var pageEntity = repository.findAll(
            byCourseLevel(courseModel.getCourseLevel())
                .and(byCourseStatus(courseModel.getCourseStatus()))
                .and(byName(courseModel.getName())), pageable);

        return pagedResourcesAssembler.toModel(pageEntity, modelAssembler);
    }

    @Override
    public CourseEntity findByCourseId(UUID courseId) {
        return findByCourseIdIfExist(courseId);
    }

    @Override
    public CourseEntity save(CourseEntity courseEntity) {
        return repository.save(courseEntity);
    }

    private CourseEntity findByCourseIdIfExist(UUID courseId) {
        return repository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }

    @Transactional
    @Override
    public void deleteById(UUID courseId) {
        delete(findByCourseIdIfExist(courseId));
    }

    @Transactional
    public void delete(CourseEntity course) {
        moduleService.deleteAllByCourse(course.getCourseId());
        repository.delete(course);
    }

    @Override
    public CourseEntity findCourseEntity(UUID courseId) {
        return findByCourseIdIfExist(courseId);
    }


}
