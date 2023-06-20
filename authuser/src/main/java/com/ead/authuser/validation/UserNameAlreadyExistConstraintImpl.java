package com.ead.authuser.validation;

import com.ead.authuser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameAlreadyExistConstraintImpl implements ConstraintValidator<UserNameAlreadyExistConstraint, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
		return !userRepository.existsByUserName(userName);
	}

}
