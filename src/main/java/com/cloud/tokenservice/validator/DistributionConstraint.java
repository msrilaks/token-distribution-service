package com.cloud.tokenservice.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DistributionValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributionConstraint {
    String message() default "Invalid Distribution";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
