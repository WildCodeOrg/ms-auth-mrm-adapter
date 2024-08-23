package org.skyhigh.msauthmrmadapter.validation.validators.flkValidator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class FlkValidatorImpl implements FlkValidator {
    @Override
    public void validate(Object entity, Class<?> entityClass, String parameterName, List<String> activeFlkList) {
        activeFlkList.forEach(activeFlkName -> {
                try {
                    Class<?> clazz = Class.forName("org.skyhigh.msauthmrmadapter.flk." + activeFlkName);
                    Method validationMethod = clazz.getMethod(
                            "validate",
                            Object.class,
                            Class.class,
                            String.class
                    );
                    Object flkObject = clazz.getConstructor().newInstance();
                    validationMethod.invoke(flkObject, entity, entityClass, parameterName);
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                         IllegalAccessException | InstantiationException e) {
                    throw new RuntimeException(e);
                }
            }
        );
    }
}

