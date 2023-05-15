package com.ead.authuser.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ead.authuser.repositories.UserRepository;

public class EmailAlreadyExistConstraintImpl implements ConstraintValidator<EmailAlreadyExistConstraint, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return !userRepository.existsByEmail(email);
	}

}
