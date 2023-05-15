package com.ead.authuser.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NickNameConstraintImpl implements ConstraintValidator<NickNameConstraint, String> {

	@Override
	public void initialize(NickNameConstraint constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String nickName, ConstraintValidatorContext constraintValidatorContext) {
		return nickName != null && !nickName.trim().isEmpty() && !nickName.contains(" ");
	}
}
