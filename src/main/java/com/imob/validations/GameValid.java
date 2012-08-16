package com.imob.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GameValidator.class)
@Documented
public @interface GameValid {
	String message() default "{com.imob.validations.GameValid.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
