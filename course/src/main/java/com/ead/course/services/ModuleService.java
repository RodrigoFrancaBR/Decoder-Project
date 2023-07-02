package com.ead.course.services;

import com.ead.course.entity.ModuleEntity;
import com.ead.course.model.ModuleModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.List;
import java.util.UUID;

public interface ModuleService {

    List<ModuleEntity> findAllByCourseId(UUID courseId);

    PagedModel<ModuleModel> findAllByTitleAndCourseId(String title, UUID courseId, Pageable pageable);

    void deleteAllByCourse(UUID courseId);

    void delete(ModuleEntity module);

    ModuleModel save(ModuleModel module);

    ModuleEntity save(ModuleEntity module);

    ModuleEntity findByCourseAndModuleId(UUID courseId, UUID moduleId);

    void deleteAllByCourseId(UUID courseId);

    ModuleEntity findByModuleId(UUID moduleId);
}
