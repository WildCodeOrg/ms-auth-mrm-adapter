package org.skyhigh.msauthmrmadapter.validation.springAspect.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.skyhigh.msauthmrmadapter.validation.validationEnums.ApiValidationType;
import org.skyhigh.msauthmrmadapter.validation.validators.paramValidator.AnnotationBasedParamValidator;
import org.skyhigh.msauthmrmadapter.validation.validators.paramValidator.FlkBasedParamValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class MethodParamValidationAspect {
    private final AnnotationBasedParamValidator annotationBasedValidator;
    private final FlkBasedParamValidator flkBasedValidator;
    private final List<String> activeFlkList;

    public MethodParamValidationAspect(
            @Qualifier("AnnotationBasedParamValidator") AnnotationBasedParamValidator annotationBasedValidator,
            @Qualifier("FlkBasedParamValidator") FlkBasedParamValidator flkBasedValidator,
            @Qualifier("ActiveFlkList") List<String> activeFlkList
    ) {
        this.annotationBasedValidator = annotationBasedValidator;
        this.flkBasedValidator = flkBasedValidator;
        this.activeFlkList = activeFlkList;
    }

    @Before(value = "@annotation(org.skyhigh.msauthmrmadapter.validation.springAspect.annotationsApi.ValidParams)")
    public void validateParameters(JoinPoint joinPoint) {
        String[] parameterNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();

        for (int i = 0; i < parameterNames.length; i++) {
            annotationBasedValidator.validate(joinPoint.getArgs()[i]);
            flkBasedValidator.validate(joinPoint.getArgs()[i], parameterNames[i], ApiValidationType.REST, activeFlkList);
        }
    }
}
