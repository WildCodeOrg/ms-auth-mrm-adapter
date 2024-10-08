package org.skyhigh.msauthmrmadapter.validation.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RequiredParameterDidNotSetException extends RequestException{
    private final String parameterName;

    @Override
    public String getCode() {
        return "01";
    }

    @Override
    public String getParameterPath() {
        return null;
    }

    @Override
    public String getParameterName() {
        return parameterName;
    }

    @Override
    public String getMessage() {
        return "Required parameter was not passed";
    }
}
