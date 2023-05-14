package com.ead.authuser.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.models.UserModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserWithStatusSpec implements Specification<UserModel> {

	private static final long serialVersionUID = -1961777641755007010L;
	
	private UserStatus userStatus;

	@Override
	public Predicate toPredicate(Root<UserModel> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return builder.equal(root.get("userStatus"), userStatus);
	}

}
