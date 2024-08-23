package org.skyhigh.msauthmrmadapter.flk;

public abstract class CommonFlk {
    protected String parameterName;
    protected Class<?> entityClass;
    protected String flkCode;
    protected String attributePath;
    protected String flkMessage;

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
