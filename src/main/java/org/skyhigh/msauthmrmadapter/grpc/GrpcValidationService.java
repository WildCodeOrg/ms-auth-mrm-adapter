package org.skyhigh.msauthmrmadapter.grpc;

public interface GrpcValidationService {
    void validate(Object entity, Class<?> entityClass, String parameterName);
}
