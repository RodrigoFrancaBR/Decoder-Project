package com.ead.course.services;

import java.util.UUID;

import com.ead.course.entity.ModuleEntity;
import com.ead.course.model.ModuleModel;

public interface ModuleService {

	void deleteAllModulesByCourseId(UUID courseId);

	void delete(ModuleEntity moduleEntity);

	ModuleModel save(ModuleModel moduleModel);

	ModuleEntity save(ModuleEntity moduleEntity);

}
