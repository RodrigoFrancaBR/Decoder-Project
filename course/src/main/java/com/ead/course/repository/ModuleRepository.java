package com.ead.course.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ead.course.entity.ModuleEntity;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity, UUID> {

	@Query(value = "SELECT m FROM ModuleEntity m WHERE m.course.courseId = :courseId")
	List<ModuleEntity> findAllByCourseId(@Param("courseId") UUID courseId);

	@Query(value = "SELECT m FROM ModuleEntity m WHERE m.course.courseId = :courseId AND m.moduleId = :moduleId")
	Optional<ModuleEntity> findModuleByCourse(@Param("courseId") UUID courseId, @Param("moduleId") UUID moduleId);
}
