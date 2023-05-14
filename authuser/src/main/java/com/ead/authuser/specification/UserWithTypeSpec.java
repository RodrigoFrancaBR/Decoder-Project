package com.ead.authuser.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserWithTypeSpec implements Specification<UserModel> {

	private static final long serialVersionUID = -7844372161240489826L;
	
	private UserType userType;

	@Override
	public Predicate toPredicate(Root<UserModel> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return builder.equal(root.get("userType"), userType);
	}

}
