package com.ead.course.services.impl;

import com.ead.course.entity.ModuleEntity;
import com.ead.course.exceptions.ModuleNotFoundException;
import com.ead.course.model.ModuleModel;
import com.ead.course.repository.ModuleRepository;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

	private final ModuleRepository repository;
	private final LessonService lessonService;

	@Override
	public List<ModuleEntity> findAllByCourseId(UUID courseId) {
		return repository.findAllByCourseId(courseId);
	}
	
	@Transactional
	@Override
	public void deleteAll(List<ModuleEntity> modules) {
		repository.deleteAll(modules);
	}

	@Override
	public void deleteAllByCourseId(UUID courseId) {
		var moduleEntityList = repository.findAllByCourseId(courseId);
		deleteAllModulesIfExist(moduleEntityList);
	}

	private void deleteAllModulesIfExist(List<ModuleEntity> modules) {
		if (!modules.isEmpty()) {
			for (ModuleEntity module : modules) {
				lessonService.deleteAllByModuleId(module.getModuleId());
			}
			repository.deleteAll(modules);
		}
	}

	@Override
	public void deleteAllByCourse(UUID courseId) {
		List<ModuleEntity> modules = repository.findAllByCourseId(courseId);
		deleteAllModulesIfExist(modules);
	}

	@Transactional
	@Override
	public void delete(ModuleEntity moduleEntity) {
		lessonService.deleteAllByModuleId(moduleEntity.getModuleId());
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
	public ModuleEntity findByCourseAndModuleId(UUID courseId, UUID moduleId) {
		return findByCourseAndModuleIdIfExist(courseId, moduleId);
	}

	private ModuleEntity findByCourseAndModuleIdIfExist(UUID courseId, UUID moduleId) {
		return repository.findModuleByCourse(courseId, moduleId)
				.orElseThrow(() -> new ModuleNotFoundException("Module not found"));
	}

	@Override
	public ModuleEntity findByCourseId(UUID courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleEntity findByModuleId(UUID moduleId) {
		return findByModuleIdIfExist(moduleId);		
	}

	private ModuleEntity findByModuleIdIfExist(UUID moduleId) {
		return repository.findById(moduleId).orElseThrow(() -> new ModuleNotFoundException("Module not found"));
	}

	@Override
	public void deleteAllModulesByCourseId(UUID courseId) {
		List<ModuleEntity> modules = repository.findAllByCourseId(courseId);
		deleteAllModulesIfExist(modules);
		
	}

}
