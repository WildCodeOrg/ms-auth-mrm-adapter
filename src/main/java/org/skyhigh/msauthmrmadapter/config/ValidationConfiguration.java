package org.skyhigh.msauthmrmadapter.config;

import org.skyhigh.msauthmrmadapter.validation.annotations.NotEmpty;
import org.skyhigh.msauthmrmadapter.validation.validators.fieldValidator.FieldValidator;
import org.skyhigh.msauthmrmadapter.validation.validators.fieldValidator.NotEmptyValidatorImpl;
import org.skyhigh.msauthmrmadapter.validation.validators.flkValidator.FlkValidatorImpl;
import org.skyhigh.msauthmrmadapter.validation.validators.paramValidator.AnnotationBasedParamValidator;
import org.skyhigh.msauthmrmadapter.validation.validators.paramValidator.FlkBasedParamValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ValidationConfiguration {
    @Bean("AnnotationBasedParamValidator")
    public AnnotationBasedParamValidator getAnnotationBasedParamValidator() {
        Map<Class<? extends Annotation>, FieldValidator> validatorMap = new HashMap<>();
        validatorMap.put(NotEmpty.class, new NotEmptyValidatorImpl());
        return new AnnotationBasedParamValidator(validatorMap);
    }

    @Bean("FlkBasedParamValidator")
    public FlkBasedParamValidator getFlkBasedValidator() {
        return new FlkBasedParamValidator(new FlkValidatorImpl());
    }
}