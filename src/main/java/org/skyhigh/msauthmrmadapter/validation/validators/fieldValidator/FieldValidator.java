package org.skyhigh.msauthmrmadapter.validation.validators.fieldValidator;

import java.lang.reflect.Field;

@FunctionalInterface
public interface FieldValidator {
    void validate(Object entity, Field field);
}
