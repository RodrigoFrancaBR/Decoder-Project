package com.ead.course.usecase;

import com.ead.course.assembler.ModuleEntityAssembler;
import com.ead.course.assembler.ModuleModelAssembler;
import com.ead.course.model.ModuleModel;
import com.ead.course.services.CourseService;
import com.ead.course.services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ModuleFacade {
    private final CourseService courseService;
    private final ModuleService moduleService;
    private final ModuleModelAssembler modelAssembler;
    private final ModuleEntityAssembler entityAssembler;

    public ModuleModel saveModule(UUID courseId, ModuleModel moduleModel) {
        var moduleEntity = entityAssembler.toEntity(moduleModel);
        moduleEntity.setCourse(courseService.findCourseEntity(courseId));
        return modelAssembler.toModel(moduleService.save(moduleEntity));
    }

    public ModuleModel updateByCourseId(UUID courseId, UUID moduleId, ModuleModel moduleModel) {
        var moduleEntity = moduleService.findByCourseAndModuleId(courseId, moduleId);
        entityAssembler.copyNonNullProperties(moduleModel, moduleEntity);
        return modelAssembler.toModel(moduleService.save(moduleEntity));
    }

    public void deleteByCourseId(UUID courseId, UUID moduleId) {
        moduleService.delete(moduleService.findByCourseAndModuleId(courseId, moduleId));
    }

    public PagedModel<ModuleModel> findAllByTitleAndCourseId(String title, UUID courseId, Pageable pageable) {
        return moduleService.findAllByTitleAndCourseId(title, courseId, pageable);
    }

    public ModuleModel findByCourseAndModuleId(UUID courseId, UUID moduleId) {
        return modelAssembler.toModel(moduleService.findByCourseAndModuleId(courseId, moduleId));
    }
}
