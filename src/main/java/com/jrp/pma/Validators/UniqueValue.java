package com.jrp.pma.Validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// The element type states what type of field this annotation will be applied to
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface UniqueValue {
	
	// this will display whenever there is an error
	String message() default "Unique Constraint violated";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default{};
}
