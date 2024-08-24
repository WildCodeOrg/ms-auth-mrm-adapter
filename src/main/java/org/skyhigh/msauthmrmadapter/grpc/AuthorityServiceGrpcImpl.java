package org.skyhigh.msauthmrmadapter.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.skyhigh.grpc.authority.AuthorityRequest;
import org.skyhigh.grpc.authority.AuthorityResponse;
import org.skyhigh.grpc.authority.AuthorityServiceGrpc;
import org.skyhigh.grpc.authority.AuthoritySuccessResponse;
import org.skyhigh.msauthmrmadapter.commonService.CommonAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

@GrpcService
public class AuthorityServiceGrpcImpl extends AuthorityServiceGrpc.AuthorityServiceImplBase {
    private static final Logger log = Logger.getLogger(AuthorityServiceGrpcImpl.class.getName());

    private final GrpcValidationService grpcValidationService;

    private final CommonAuthorityService commonAuthorityService;

    @Autowired
    public AuthorityServiceGrpcImpl(GrpcValidationService grpcValidationService, CommonAuthorityService commonAuthorityService) {
        this.grpcValidationService = grpcValidationService;
        this.commonAuthorityService = commonAuthorityService;
    }

    @Override
    public void getUserAuthorities(AuthorityRequest authorityGrpcRequest,
                                   StreamObserver<AuthorityResponse> responseObserver) {
        log.info("getUserAuthorities from grpc request: " + authorityGrpcRequest);

        grpcValidationService.validate(
                authorityGrpcRequest,
                authorityGrpcRequest.getClass(),
                "authorityGrpcRequest"
        );

        List<String> authorities = commonAuthorityService.getAuthorities(authorityGrpcRequest.getLogin());

        AuthoritySuccessResponse.Builder successBuilder = AuthoritySuccessResponse.newBuilder();
        for (String authority : authorities)
            successBuilder.addAuthority(authority);

        AuthorityResponse authorityResponse = AuthorityResponse.newBuilder()
                .setAuthoritySuccessResponse(successBuilder.build())
                .build();

        responseObserver.onNext(authorityResponse);
        responseObserver.onCompleted();
    }
}
