package com.ead.course.repository;

import com.ead.course.entity.CourseUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseUserRepository extends JpaRepository<CourseUserEntity, UUID>, JpaSpecificationExecutor<CourseUserEntity> {
//    interface CourseSpecification {
//        // posso por um único predicado aqui igual o buildEqualCourseIdPredicate do moduleRepository
//        static Specification<CourseEntity> byCourseLevel(CourseLevel courseLevel) {
//            return (root, query, builder) ->
//                    builder.equal(root.get("courseLevel"), courseLevel);
//        }
//
//        static Specification<CourseEntity> byCourseStatus(CourseStatus courseStatus) {
//            return (root, query, builder) ->
//                    builder.equal(root.get("courseStatus"), courseStatus);
//        }
//
//        static Specification<CourseEntity> containCourseName(String name) {
//            return (root, query, builder) ->
//                    builder.like(root.get("name"), "%" + name + "%");
//        }
//    }
}