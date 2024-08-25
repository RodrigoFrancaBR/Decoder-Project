package com.ead.course.specifications;

import com.ead.course.entity.CourseEntity;
import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CourseSpecificationFactory {

    public static Specification<CourseEntity> byUserId(UUID userId) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(userId)
                .map(existUserId -> root.join("coursesUsers"))
                .map(coursesUsers -> cb.equal(coursesUsers.get("userId"), userId))
                .ifPresent(predicates::add);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<CourseEntity> byCourseLevel(CourseLevel courseLevel) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(courseLevel)
                .map(existCourseLevel -> equals(cb, root.get("courseLevel"), existCourseLevel))
                .ifPresent(predicates::add);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<CourseEntity> byCourseStatus(CourseStatus courseStatus) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(courseStatus)
                .map(existCourseStatus -> equals(cb, root.get("courseStatus"), existCourseStatus))
                .ifPresent(predicates::add);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<CourseEntity> byName(String name) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(name)
                .map(existName -> like(cb, root.get("name"), existName))
                .ifPresent(predicates::add);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * pode criar uma interface para ter esses 2 metodos
     * assim todas as especificações Lesson Module etc.. implementam
     * dependendo fazer uma interface default
     */
    private static Predicate equals(CriteriaBuilder cb, Path<?> field, Object value) {
        return cb.equal(field, value);
    }

    private static Predicate like(CriteriaBuilder cb, Path<String> field, String searchTerm) {
        return cb.like(field, "%" + searchTerm + "%");
    }
}