package org.skyhigh.msauthmrmadapter.flk;

import lombok.Getter;
import lombok.Setter;
import org.skyhigh.grpc.authority.AuthorityErrorCode;

@Setter
@Getter
public abstract class CommonGrpcFlk extends CommonFlk{
    private String flkCode;
    private AuthorityErrorCode errorCode;

    public CommonGrpcFlk(
            String parameterName,
            Class<?> entityClass,
            String flkCode,
            AuthorityErrorCode errorCode
    ) {
        super(parameterName, entityClass);
        this.flkCode = flkCode;
        this.errorCode = errorCode;
    }

    @Override
    abstract protected void validate(Object entity, Class<?> entityClass, String parameterName);
}
