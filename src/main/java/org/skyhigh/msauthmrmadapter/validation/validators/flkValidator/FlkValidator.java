package org.skyhigh.msauthmrmadapter.validation.validators.flkValidator;

import java.util.List;

@FunctionalInterface
public interface FlkValidator {
    void validate(Object entity, Class<?> entityClass, String parameterName, List<String> activeFlkList);
}
