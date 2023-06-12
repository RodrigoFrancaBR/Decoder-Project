package com.ead.course.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.ead.course.entity.ModuleEntity;
import com.ead.course.model.ModuleModel;

public interface ModuleService {

	void deleteAllByCourse(UUID courseId);

	void delete(ModuleEntity moduleEntity);

	ModuleModel save(ModuleModel moduleModel);

	ModuleEntity save(ModuleEntity moduleEntity);

	ModuleEntity findByCourse(UUID courseId, UUID moduleId);

	List<ModuleEntity> findAllByCourse(UUID courseId);

}
