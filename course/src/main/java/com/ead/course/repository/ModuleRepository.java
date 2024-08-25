package com.ead.course.repository;

import com.ead.course.entity.CourseEntity;
import com.ead.course.entity.ModuleEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static java.util.Optional.ofNullable;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity, UUID>, JpaSpecificationExecutor<ModuleEntity> {

    @Query(value = "SELECT m FROM ModuleEntity m WHERE m.course.courseId = :courseId")
    List<ModuleEntity> findAllByCourseId(@Param("courseId") UUID courseId);

    @Query(value = "SELECT m FROM ModuleEntity m WHERE m.course.courseId = :courseId AND m.moduleId = :moduleId")
    Optional<ModuleEntity> findModuleByCourse(@Param("courseId") UUID courseId, @Param("moduleId") UUID moduleId);

    interface ModuleSpecification {
        static Specification<ModuleEntity> byTitleAndCourseId(final UUID courseId, String title) {
            return (root, query, builder) -> {

                List<Predicate> predicates = new ArrayList<>();

                ofNullable(title)
                        .map(buildLikeTitlePredicate(root, builder))
                        .ifPresent(predicates::add);

                var predicate = buildEqualCourseIdPredicate(builder, root, query, courseId);

                predicates.add(predicate);

                return builder.and(predicates.toArray(new Predicate[0]));
            };
        }

        private static Function<String, Predicate> buildLikeTitlePredicate(Root<ModuleEntity> module, CriteriaBuilder builder) {
            return title -> builder.like(module.get("title"), "%" + title + "%");
        }

        private static Predicate buildEqualCourseIdPredicate(CriteriaBuilder builder, Root<ModuleEntity> module, CriteriaQuery<?> query, UUID courseId) {
            // query.distinct(true); // não fez diferença
            // Root<ModuleEntity> module = root;
            Root<CourseEntity> course = query.from(CourseEntity.class);
            Expression<Collection<ModuleEntity>> courseModules = course.get("modules");

            return builder.and(builder.equal(course.get("courseId"), courseId), builder.isMember(module, courseModules));
        }
    }
}