package com.ead.course.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ead.course.entity.LessonEntity;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {

	@Query(value = "SELECT l FROM LessonEntity l WHERE l.module.moduleId = :moduleId")
	List<LessonEntity> findAllByModuleId(@Param("moduleId") UUID moduleId);

	@Query(value = "SELECT l FROM LessonEntity l WHERE l.module.moduleId = :moduleId AND l.lessonId = :lessonId")
	Optional<LessonEntity> findByModuleAndLessonId(@Param("moduleId") UUID moduleId, @Param("lessonId") UUID lessonId);
}
