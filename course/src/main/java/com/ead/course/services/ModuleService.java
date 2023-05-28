package com.ead.course.services;

import java.util.UUID;

import com.ead.course.entity.ModuleEntity;

public interface ModuleService {

	void deleteAllModulesByCourseId(UUID courseId);

	void delete(ModuleEntity module);

}
