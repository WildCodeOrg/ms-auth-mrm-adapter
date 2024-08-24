package org.skyhigh.msauthmrmadapter.validation.validators.flkValidator;

import org.skyhigh.msauthmrmadapter.validation.validationEnums.ApiValidationType;

import java.util.List;

@FunctionalInterface
public interface FlkValidator {
    void validate(Object entity, Class<?> entityClass, String parameterName,
                  ApiValidationType apiValidationType, List<String> activeFlkList);
}
