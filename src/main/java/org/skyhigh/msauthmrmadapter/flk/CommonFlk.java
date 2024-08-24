package org.skyhigh.msauthmrmadapter.flk;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class CommonFlk {
    private String parameterName;
    private Class<?> entityClass;
    private String flkCode;
    private String attributePath;
    private String flkMessage;

    public CommonFlk(
            String parameterName,
            Class<?> entityClass,
            String flkCode,
            String attributePath,
            String flkMessage
    ) {
        this.parameterName = parameterName;
        this.entityClass = entityClass;
        this.flkCode = flkCode;
        this.attributePath = attributePath;
        this.flkMessage = flkMessage;
    }

    abstract void validate(Object entity, Class<?> entityClass, String parameterName);

    protected boolean isSuitable(Class<?> entityClass, String parameterName) {
        return this.entityClass.isAssignableFrom(entityClass) && this.parameterName.equals(parameterName);
    }
}
