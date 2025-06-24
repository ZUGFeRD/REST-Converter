package org.mustangproject.server.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = VersionValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidVersion {
    String message() default "Invalid ZUGFeRD version";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
