package org.skyhigh.msauthmrmadapter.validation.validators.paramValidator;

import jakarta.validation.ValidationException;
import org.skyhigh.msauthmrmadapter.validation.validators.flkValidator.FlkValidator;

import java.util.List;

public class FlkBasedParamValidator {
    private final FlkValidator flkValidator;

    public FlkBasedParamValidator(FlkValidator flkValidator) {
        this.flkValidator = flkValidator;
    }

    public void validate(Object param, String parameterName, List<String> activeFlkList) {
        if (param == null)
            throw new ValidationException("Passed param is null");
        Class<?> clazz = param.getClass();
        flkValidator.validate(param, clazz, parameterName, activeFlkList);
    }
}
