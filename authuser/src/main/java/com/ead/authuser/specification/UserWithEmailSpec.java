package com.ead.authuser.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ead.authuser.models.UserModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserWithEmailSpec implements Specification<UserModel> {
	// poderia ser uma indeterface com método default

	private static final long serialVersionUID = 4735832117376585820L;
	
	private String email;

	@Override
	public Predicate toPredicate(Root<UserModel> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		var emailParameter = new StringBuilder().append("%").append(email).append("%").toString();
		return builder.like(root.get("email"), emailParameter);
	}

}
