package com.example.demo.validators;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PartInventoryValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPartInventoryMaximum {
    String message() default "Inventory cannot exceed maximum value";
    Class [] groups() default {};
    Class [] payload() default {};
}
