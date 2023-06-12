package com.ead.course.services.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
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
	public void deleteAllByCourse(UUID courseId) {
		List<ModuleEntity> modules = repository.findAllByCourse(courseId);
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
	public ModuleEntity findByCourse(UUID courseId, UUID moduleId) {
		return findModuleByCourseIFExist(courseId, moduleId);
	}

	private ModuleEntity findModuleByCourseIFExist(UUID courseId, UUID moduleId) {
		return repository.findModuleByCourse(courseId, moduleId)
				.orElseThrow(() -> new ModuleNotFoundException("Module not found"));
	}

	@Override
	public List<ModuleEntity> findAllByCourse(UUID courseId) {
		return repository.findAllByCourse(courseId);		
	}

}
