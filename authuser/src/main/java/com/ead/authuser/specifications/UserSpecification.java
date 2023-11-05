package com.ead.authuser.specifications;

import com.ead.authuser.entity.UserCourseEntity;
import com.ead.authuser.entity.UserCourseEntity_;
import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.entity.UserEntity_;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ead.authuser.entity.UserEntity_.usersCourses;

@Builder
@AllArgsConstructor
public class UserSpecification implements Specification<UserEntity> {

    private String email;
    private String fullName;
    private UserStatus userStatus;
    private UserType userType;
    private UUID courseId;
    private final List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        Optional.ofNullable(email)
            .map(existEmail -> like(cb, root.get(UserEntity_.email), email))
            .ifPresent(predicates::add);

        Optional.ofNullable(fullName)
            .map(existFullName -> like(cb, root.get(UserEntity_.fullName), fullName))
            .ifPresent(predicates::add);

        Optional.ofNullable(userStatus)
            .map(existUserStatus -> equals(cb, root.get(UserEntity_.userStatus), userStatus))
            .ifPresent(predicates::add);

        Optional.ofNullable(userType)
            .map(fieldUserStatus -> equals(cb, root.get(UserEntity_.userType), userType))
            .ifPresent(predicates::add);

        Optional.ofNullable(courseId)
            .map(fieldCourseId -> courseIdPredicate(cb, root.join(UserEntity_.usersCourses), courseId))
            .ifPresent(predicates::add);

        final var size = predicates.size();
        return cb.and(predicates.toArray(new Predicate[size]));
    }

    private Predicate equals(CriteriaBuilder cb, Path<?> field, Object value) {
        return cb.equal(field, value);
    }

    private Predicate like(CriteriaBuilder cb, Path<String> field, String searchTerm) {
        return cb.like(field, "%" + searchTerm + "%");
    }

    private Predicate courseIdPredicate(CriteriaBuilder cb, Join<UserEntity, UserCourseEntity> join, Object value) {
        return cb.equal(join.get(UserCourseEntity_.courseId), courseId);
    }
}