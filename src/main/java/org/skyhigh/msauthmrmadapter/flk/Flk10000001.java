package org.skyhigh.msauthmrmadapter.flk;

import org.skyhigh.grpc.authority.AuthorityErrorCode;
import org.skyhigh.grpc.authority.AuthorityRequest;
import org.skyhigh.msauthmrmadapter.validation.exceptions.GrpcFlkException;

public class Flk10000001 extends CommonGrpcFlk {
    private final Integer minLoginLength = 8;
    private final Integer maxLoginLength = 20;

    public Flk10000001() {
        super(
                "authorityGrpcRequest",
                AuthorityRequest.class,
                "10000001",
                AuthorityErrorCode.INCORRECT_LOGIN_LENGTH
        );
    }

    @Override
    protected void validate(Object entity, Class<?> entityClass, String parameterName) {
        if (isSuitable(entityClass, parameterName))
            if (((AuthorityRequest) entity).getLogin().length() < minLoginLength ||
                    ((AuthorityRequest) entity).getLogin().length() > maxLoginLength)
                throw new GrpcFlkException(
                        getErrorCode()
                );
    }
}
