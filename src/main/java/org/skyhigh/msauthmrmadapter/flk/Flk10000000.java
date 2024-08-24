package org.skyhigh.msauthmrmadapter.flk;

import org.skyhigh.msauthmrmadapter.validation.exceptions.FlkException;

import java.text.MessageFormat;

/**
 * Flk10000000 - ФЛК на проверку длины логина
 */
public class Flk10000000 extends CommonFlk {
    private final Integer minLoginLength = 8;
    private final Integer maxLoginLength = 20;

    public Flk10000000() {
        super(
                "login",
                String.class,
                "10000000",
                "/{login}",
                "Длина логина должна быть не менее {0} и не более {1} символов."
        );

        setFlkMessage(MessageFormat.format(
                getFlkMessage(),
                minLoginLength,
                maxLoginLength
        ));
    }

    @Override
    public void validate(Object entity, Class<?> entityClass, String parameterName) throws FlkException {
        if (isSuitable(entityClass, parameterName))
            if (((String) entity).length() < minLoginLength || ((String) entity).length() > maxLoginLength)
                throw new FlkException(
                        getFlkCode(),
                        getAttributePath(),
                        getFlkMessage()
                );
    }
}
