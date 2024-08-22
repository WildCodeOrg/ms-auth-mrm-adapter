package org.skyhigh.msauthmrmadapter.validation.validators.paramValidator;

@FunctionalInterface
public interface ParamValidator {
    void validate(Object bean);
}
