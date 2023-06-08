package com.ead.course.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ead.course.entity.ModuleEntity;
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
	public void delete(ModuleEntity module) {
		lessonService.deleteAllLessonsByModuleId(module.getModuleId());
		repository.delete(module);
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
	public void findModuleByCourse(UUID courseId, UUID moduleId) {
		repository.findModuleByCourse(courseId, moduleId);
	}

}
