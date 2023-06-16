package com.ead.course.services;

import java.util.List;
import java.util.UUID;

import com.ead.course.entity.ModuleEntity;
import com.ead.course.model.ModuleModel;

public interface ModuleService {

	List<ModuleEntity> findAllByCourseId(UUID courseId);	

	void deleteAll(List<ModuleEntity> modules);

	ModuleEntity findByCourseId(UUID courseId);

	void deleteAllByCourse(UUID courseId);

	void delete(ModuleEntity module);

	ModuleModel save(ModuleModel module);

	ModuleEntity save(ModuleEntity module);

	ModuleEntity findByCourseAndModuleId(UUID courseId, UUID moduleId);

	void deleteAllByCourseId(UUID courseId);

	ModuleEntity findByModuleId(UUID moduleId);

	void deleteAllModulesByCourseId(UUID courseId);

}
