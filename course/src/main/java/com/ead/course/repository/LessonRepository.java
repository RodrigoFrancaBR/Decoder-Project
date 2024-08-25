package com.ead.course.repository;

import com.ead.course.entity.LessonEntity;
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
public interface LessonRepository extends JpaRepository<LessonEntity, UUID>, JpaSpecificationExecutor<LessonEntity> {

    @Query(value = "SELECT l FROM LessonEntity l WHERE l.module.moduleId = :moduleId")
    List<LessonEntity> findAllByModuleId(@Param("moduleId") UUID moduleId);

    @Query(value = "SELECT l FROM LessonEntity l WHERE l.module.moduleId = :moduleId AND l.lessonId = :lessonId")
    Optional<LessonEntity> findByModuleAndLessonId(@Param("moduleId") UUID moduleId, @Param("lessonId") UUID lessonId);

    interface LessonSpecification {
        static Specification<LessonEntity> byTitleAndModuleId(final UUID moduleId, String title) {
            return (root, query, builder) -> {

                List<Predicate> predicates = new ArrayList<>();

                ofNullable(title)
                        .map(buildLikeTitlePredicate(root, builder))
                        .ifPresent(predicates::add);

                var predicate = buildEqualModuleIdPredicate(builder, root, query, moduleId);

                predicates.add(predicate);

                return builder.and(predicates.toArray(new Predicate[0]));
            };
        }

        private static Function<String, Predicate> buildLikeTitlePredicate(Root<LessonEntity> lesson, CriteriaBuilder builder) {
            return title -> builder.like(lesson.get("title"), "%" + title + "%");
        }

        private static Predicate buildEqualModuleIdPredicate(CriteriaBuilder builder, Root<LessonEntity> lesson, CriteriaQuery<?> query, UUID moduleId) {
            // query.distinct(true); // não fez diferença
            // Root<ModuleEntity> module = root;
            Root<ModuleEntity> module = query.from(ModuleEntity.class);
            Expression<Collection<LessonEntity>> moduleLessons = module.get("lessons");
            return builder.and(builder.equal(module.get("moduleId"), moduleId), builder.isMember(lesson, moduleLessons));
        }
    }
}
