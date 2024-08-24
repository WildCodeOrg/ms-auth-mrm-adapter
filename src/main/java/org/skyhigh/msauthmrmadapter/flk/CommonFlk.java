package org.skyhigh.msauthmrmadapter.flk;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class CommonFlk {
    private final String parameterName;
    private final Class<?> entityClass;

    public CommonFlk(
            String parameterName,
            Class<?> entityClass
    ) {
        this.parameterName = parameterName;
        this.entityClass = entityClass;
    }

    protected boolean isSuitable(Class<?> entityClass, String parameterName) {
        return this.entityClass.isAssignableFrom(entityClass) && this.parameterName.equals(parameterName);
    }

    abstract protected void validate(Object entity, Class<?> entityClass, String parameterName);
}
