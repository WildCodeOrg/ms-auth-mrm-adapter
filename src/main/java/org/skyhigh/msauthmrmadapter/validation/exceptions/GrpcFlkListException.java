package org.skyhigh.msauthmrmadapter.validation.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.skyhigh.grpc.authority.AuthorityErrorCode;

import java.util.Arrays;

@Data
@EqualsAndHashCode(callSuper = true)
public class GrpcFlkListException extends RuntimeException {
    private AuthorityErrorCode[] errorCodes;

    public GrpcFlkListException(AuthorityErrorCode[] errorCodes) {
        super(String.join(", ", Arrays.stream(errorCodes).map(AuthorityErrorCode::name).toList()));
        this.errorCodes = errorCodes;
    }
}
