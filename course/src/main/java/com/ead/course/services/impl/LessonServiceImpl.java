package com.ead.course.services.impl;

import com.ead.course.assembler.LessonModelAssembler;
import com.ead.course.entity.LessonEntity;
import com.ead.course.exceptions.LessonNotFoundException;
import com.ead.course.model.LessonModel;
import com.ead.course.repository.LessonRepository;
import com.ead.course.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.ead.course.repository.LessonRepository.LessonSpecification.byTitleAndModuleId;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository repository;

    private final LessonModelAssembler modelAssembler;
    private final PagedResourcesAssembler<LessonEntity> pagedResourcesAssembler;

    @Override
    public void deleteAllByModuleId(UUID moduleId) {
        List<LessonEntity> lessons = repository.findAllByModuleId(moduleId);
        if (!lessons.isEmpty()) {
            repository.deleteAll(lessons);
        }
    }

    @Override
    public PagedModel<LessonModel> findAllByTitleAndModuleId(String title, UUID moduleId, Pageable pageable) {
        var pageEntity = repository.findAll(LessonRepository.LessonSpecification.byTitleAndModuleId(moduleId, title), pageable);
        return pagedResourcesAssembler.toModel(pageEntity, modelAssembler);
    }

    @Override
    public LessonEntity save(LessonEntity lessonEntity) {
        return repository.save(lessonEntity);
    }

    @Override
    public LessonEntity findByModuleAndLessonId(UUID moduleId, UUID lessonId) {
        return findByModuleAndLessonIdIfExist(moduleId, lessonId);
    }

    private LessonEntity findByModuleAndLessonIdIfExist(UUID moduleId, UUID lessonId) {
        return repository.findByModuleAndLessonId(moduleId, lessonId)
                .orElseThrow(() -> new LessonNotFoundException("Lesson not found"));
    }

    @Override
    public void delete(LessonEntity lessonEntity) {
        repository.delete(lessonEntity);
    }

}
