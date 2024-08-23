package org.skyhigh.msauthmrmadapter.validation.validators.paramValidator;

import jakarta.validation.ValidationException;
import org.skyhigh.msauthmrmadapter.validation.validators.fieldValidator.FieldValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class AnnotationBasedParamValidator {

    private final Map<Class<? extends Annotation>, FieldValidator> fieldValidationFunctions;
    private final Set<Class<? extends Annotation>> supportedFieldAnnotations;

    public AnnotationBasedParamValidator(Map<Class<? extends Annotation>, FieldValidator> fieldValidationFunctions) {
        this.fieldValidationFunctions = fieldValidationFunctions;
        supportedFieldAnnotations = this.fieldValidationFunctions.keySet();
    }

    public void validate(Object param) {
        if (param == null)
            throw new ValidationException("Passed param is null");

        Class<?> clazz = param.getClass();

        if (clazz.equals(String.class) || clazz.equals(UUID.class) || clazz.equals(Integer.class))
            return;

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            supportedFieldAnnotations.stream()
                    .filter(field::isAnnotationPresent)
                    .map(fieldValidationFunctions::get)
                    .forEach(fieldValidator -> fieldValidator.validate(param, field));
        }
    }
}
