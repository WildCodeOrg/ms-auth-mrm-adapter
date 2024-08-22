package org.skyhigh.msauthmrmadapter.config;

import org.skyhigh.msauthmrmadapter.validation.annotations.NotEmpty;
import org.skyhigh.msauthmrmadapter.validation.validators.fieldValidator.FieldValidator;
import org.skyhigh.msauthmrmadapter.validation.validators.fieldValidator.NotEmptyValidatorImpl;
import org.skyhigh.msauthmrmadapter.validation.validators.paramValidator.AnnotationBasedParamValidatorImpl;
import org.skyhigh.msauthmrmadapter.validation.validators.paramValidator.ParamValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ValidationConfiguration {
    @Bean
    public ParamValidator getParamValidator() {
        Map<Class<? extends Annotation>, FieldValidator> validatorMap = new HashMap<>();
        validatorMap.put(NotEmpty.class, new NotEmptyValidatorImpl());
        return new AnnotationBasedParamValidatorImpl(validatorMap);
    }
}