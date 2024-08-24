package org.skyhigh.msauthmrmadapter.flk;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class CommonRestFlk extends CommonFlk {
    private String parameterName;
    private Class<?> entityClass;
    private String flkCode;
    private String attributePath;
    private String flkMessage;

    public CommonRestFlk(
            String parameterName,
            Class<?> entityClass,
            String flkCode,
            String attributePath,
            String flkMessage
    ) {
        super(parameterName, entityClass);
        this.flkCode = flkCode;
        this.attributePath = attributePath;
        this.flkMessage = flkMessage;
    }

    @Override
    abstract protected void validate(Object entity, Class<?> entityClass, String parameterName);
}
