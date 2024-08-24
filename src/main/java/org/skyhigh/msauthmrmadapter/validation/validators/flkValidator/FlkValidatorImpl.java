package org.skyhigh.msauthmrmadapter.validation.validators.flkValidator;

import org.skyhigh.msauthmrmadapter.validation.exceptions.FlkException;
import org.skyhigh.msauthmrmadapter.validation.exceptions.MultipleFlkException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FlkValidatorImpl implements FlkValidator {
    @Override
    public void validate(Object entity, Class<?> entityClass, String parameterName, List<String> activeFlkList) {
        List<FlkException> flkExceptions = new ArrayList<>();

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
                    } catch (ClassNotFoundException | NoSuchMethodException |
                             IllegalAccessException | InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        if (e.getTargetException() instanceof FlkException) {
                            flkExceptions.add((FlkException) e.getTargetException());
                        }
                    }
                }
        );

        if (!flkExceptions.isEmpty())
            throw new MultipleFlkException(flkExceptions);
    }
}

