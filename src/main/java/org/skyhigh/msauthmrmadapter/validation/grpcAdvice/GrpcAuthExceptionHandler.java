package org.skyhigh.msauthmrmadapter.validation.grpcAdvice;

import com.google.protobuf.Any;
import com.google.rpc.Code;
import io.grpc.StatusRuntimeException;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.skyhigh.grpc.authority.AuthorityErrorCode;
import org.skyhigh.grpc.authority.AuthorityExceptionResponse;
import org.skyhigh.grpc.authority.AuthorityResponse;
import org.skyhigh.msauthmrmadapter.validation.exceptions.GrpcFlkListException;

@GrpcAdvice
public class GrpcAuthExceptionHandler {
    @GrpcExceptionHandler(GrpcFlkListException.class)
    public StatusRuntimeException handleValidationError(GrpcFlkListException cause) {
        AuthorityExceptionResponse.Builder authorityExceptionResponseBuilder = AuthorityExceptionResponse.newBuilder();
        for (AuthorityErrorCode errorCode : AuthorityErrorCode.values())
            authorityExceptionResponseBuilder.addErrorCode(errorCode);

        AuthorityResponse exceptionResponse = AuthorityResponse.newBuilder()
                .setAuthorityExceptionResponse(authorityExceptionResponseBuilder.build())
                .build();

        Status status = Status.newBuilder()
                .setCode(Code.INVALID_ARGUMENT.getNumber())
                .setMessage("Invalid argument")
                .addDetails(Any.pack(exceptionResponse))
                .build();

        return StatusProto.toStatusRuntimeException(status);
    }
}
