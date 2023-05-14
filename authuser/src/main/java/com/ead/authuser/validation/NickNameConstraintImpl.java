package com.ead.authuser.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class NickNameConstraintImpl implements ConstraintValidator<UserNameConstraint, String> {
	
    @Override
    public void initialize(UserNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String nickName, ConstraintValidatorContext constraintValidatorContext) {
        return nickName != null && !nickName.trim().isEmpty() && !nickName.contains(" ");
    }
}
