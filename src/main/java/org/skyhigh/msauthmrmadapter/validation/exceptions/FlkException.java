package org.skyhigh.msauthmrmadapter.validation.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FlkException extends RequestException {
    private final String flkCode;
    private final String parameterPath;
    private final String flkMessage;

    @Override
    public String getCode() {
        return flkCode;
    }

    @Override
    public String getParameterPath() {
        return parameterPath;
    }

    @Override
    public String getParameterName() {
        return null;
    }

    @Override
    public String getMessage() {
        return flkMessage;
    }
}
