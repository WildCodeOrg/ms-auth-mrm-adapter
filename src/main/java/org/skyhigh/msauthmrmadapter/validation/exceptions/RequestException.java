package org.skyhigh.msauthmrmadapter.validation.exceptions;

public abstract class RequestException extends RuntimeException{
    public abstract String getCode();

    public abstract String getParameterPath();

    public abstract String getParameterName();

    @Override
    public abstract String getMessage();
}
