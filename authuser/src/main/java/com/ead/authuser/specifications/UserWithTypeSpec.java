package com.ead.authuser.specifications;

import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.entity.UserEntity_;
import com.ead.authuser.enums.UserType;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class UserWithTypeSpec implements Specification<UserEntity> {

    private UserType userType;

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path<?> x = root.get(UserEntity_.userType);
        return builder.equal(root.get(UserEntity_.userType), userType);
    }
}