package org.skyhigh.msauthmrmadapter.flk;

import org.skyhigh.msauthmrmadapter.validation.exceptions.FlkException;

/**
 * Flk10000000 - ФЛК на проверку длины логина
 */
public class Flk10000000 extends CommonFlk {
    private final int minLoginLength = 8;
    private final int maxLoginLength = 20;

    public Flk10000000() {
        super(
                "login",
                String.class,
                "10000000",
                "/{login}",
                "Длина логина должна быть не менее 8 и не более 20 символов."
        );
    }

    @Override
    public void validate(Object entity, Class<?> entityClass, String parameterName) {
        if (isSuitable(entityClass, parameterName))
            if (((String) entity).length() < minLoginLength || ((String) entity).length() > maxLoginLength)
                throw new FlkException(
                        super.flkCode,
                        super.attributePath,
                        super.flkMessage
                );
    }
}
