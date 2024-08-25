package com.ead.authuser.specifications;

import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.entity.UserEntity_;
import com.ead.authuser.enums.UserStatus;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class UserWithStatusSpec implements Specification<UserEntity> {

    private UserStatus userStatus;

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.equal(root.get(UserEntity_.userStatus), userStatus);
    }
}