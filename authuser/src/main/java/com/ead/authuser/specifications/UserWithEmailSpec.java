package com.ead.authuser.specifications;

import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.entity.UserEntity_;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class UserWithEmailSpec implements Specification<UserEntity> {

    private String email;

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        var pattern = new StringBuilder()
            .append("%")
            .append(email)
            .append("%")
            .toString();
        return builder.like(root.get(UserEntity_.email), pattern);
    }
}