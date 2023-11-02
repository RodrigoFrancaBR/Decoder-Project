package com.ead.authuser.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UserNameAlreadyExistConstraintImpl.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameAlreadyExistConstraint {
	String message() default "UserName already exist";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
