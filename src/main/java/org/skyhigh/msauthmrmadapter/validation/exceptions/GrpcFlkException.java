package org.skyhigh.msauthmrmadapter.validation.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.skyhigh.grpc.authority.AuthorityErrorCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GrpcFlkException extends RuntimeException {
    private AuthorityErrorCode errorCode;

    public GrpcFlkException(AuthorityErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }
}
