package org.skyhigh.msauthmrmadapter.rest.controllerAdvice;

import org.skyhigh.msauthmrmadapter.model.dto.Error;
import org.skyhigh.msauthmrmadapter.model.dto.ErrorsDTO;
import org.skyhigh.msauthmrmadapter.validation.exceptions.MultipleFlkException;
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
    @ExceptionHandler({RequiredParameterDidNotSetException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected Error requestParameterExceptionHandler(RequestException ex) {
        return Error.builder()
                .code(ex.getCode())
                .attributeName(ex.getParameterName())
                .message(ex.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler({MultipleFlkException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorsDTO multipleFlkExceptionHandler(MultipleFlkException ex) {
        return ErrorsDTO.builder()
                .errors(
                        ex.getFlkExceptions().stream().map(x -> Error.builder()
                                .code(x.getCode())
                                .attributePath(x.getParameterPath())
                                .message(x.getMessage())
                                .build())
                            .toList()
                )
                .build();
    }
}






