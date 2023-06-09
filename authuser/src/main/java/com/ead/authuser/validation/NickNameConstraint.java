package com.ead.authuser.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NickNameConstraintImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NickNameConstraint {	
    String message() default "Invalid NickName";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
