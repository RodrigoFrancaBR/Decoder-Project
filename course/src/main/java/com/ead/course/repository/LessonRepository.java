package com.ead.course.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ead.course.entity.LessonEntity;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {

}
