package org.skyhigh.msauthmrmadapter.rest.controllerAdvice;

import org.skyhigh.msauthmrmadapter.model.dto.ErrorDTO;
import org.skyhigh.msauthmrmadapter.validation.exceptions.FlkException;
import org.skyhigh.msauthmrmadapter.validation.exceptions.RequestException;
import org.skyhigh.msauthmrmadapter.validation.exceptions.RequiredParameterDidNotSetException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler({RequiredParameterDidNotSetException.class, FlkException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDTO requestParameterExceptionHandler(RequestException ex) {
        return ErrorDTO.builder()
                .code(ex.getCode())
                .attributePath(ex.getParameterPath())
                .attributeName(ex.getParameterName())
                .message(ex.getMessage())
                .build();
    }
}






