package org.skyhigh.msauthmrmadapter.grpc;

import org.skyhigh.msauthmrmadapter.validation.validationEnums.ApiValidationType;
import org.skyhigh.msauthmrmadapter.validation.validators.paramValidator.FlkBasedParamValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrpcValidationServiceImpl implements GrpcValidationService {
    private final FlkBasedParamValidator flkBasedValidator;

    private final List<String> activeFlkList;

    private final ApiValidationType apiValidationType = ApiValidationType.gRPC;

    public GrpcValidationServiceImpl(
            @Qualifier("FlkBasedParamValidator") FlkBasedParamValidator flkBasedValidator,
            @Qualifier("ActiveFlkList") List<String> activeFlkList
    ) {
        this.flkBasedValidator = flkBasedValidator;
        this.activeFlkList = activeFlkList;
    }

    @Override
    public void validate(Object entity, Class<?> entityClass, String parameterName) {
        flkBasedValidator.validate(
                entity,
                parameterName,
                apiValidationType,
                activeFlkList
        );
    }
}
