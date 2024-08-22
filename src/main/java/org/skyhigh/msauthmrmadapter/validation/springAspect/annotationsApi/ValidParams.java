package org.skyhigh.msauthmrmadapter.validation.springAspect.annotationsApi;

import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidParams {
}
