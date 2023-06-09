package com.ead.course.services.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ead.course.entity.ModuleEntity;
import com.ead.course.exceptions.ModuleNotFoundException;
import com.ead.course.model.ModuleModel;
import com.ead.course.repository.ModuleRepository;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

	private final ModuleRepository repository;
	private final LessonService lessonService;

	@Override
	public void deleteAllModulesByCourseId(UUID courseId) {
		List<ModuleEntity> modules = repository.findAllModulesByCourseId(courseId);
		deleteAllModulesIfExist(modules);
	}

	private void deleteAllModulesIfExist(List<ModuleEntity> modules) {
		if (!modules.isEmpty()) {
			for (ModuleEntity module : modules) {
				lessonService.deleteAllLessonsByModuleId(module.getModuleId());
			}
			repository.deleteAll(modules);
		}
	}

	@Transactional
	@Override
	public void delete(ModuleEntity moduleEntity) {
		lessonService.deleteAllLessonsByModuleId(moduleEntity.getModuleId());
		repository.delete(moduleEntity);
	}

	@Override
	public ModuleModel save(ModuleModel moduleModel) {
		return null;
	}

	@Override
	public ModuleEntity save(ModuleEntity moduleEntity) {
		return repository.save(moduleEntity);
	}

	@Override
	public ModuleEntity findModuleByCourse(UUID courseId, UUID moduleId) {
		return repository.findModuleByCourse(courseId, moduleId)
				.orElseThrow(() -> new ModuleNotFoundException("Module not found"));
	}

}
