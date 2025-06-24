package org.mustangproject.server.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VersionValidator implements ConstraintValidator<ValidVersion, Integer> {
    @Override
    public boolean isValid(Integer version, ConstraintValidatorContext context) {
        if(version == null) {
            return false;
        }
        return (version >= 1) && (version <= 2);
    }
}
