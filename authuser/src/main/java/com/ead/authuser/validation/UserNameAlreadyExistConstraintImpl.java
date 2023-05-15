package com.ead.authuser.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ead.authuser.repositories.UserRepository;

public class UserNameAlreadyExistConstraintImpl implements ConstraintValidator<UserNameAlreadyExistConstraint, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
		return !userRepository.existsByUserName(userName);
	}

}
