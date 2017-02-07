package com.pvt.exceptions;

/**
 * Created by sssergey83 on 05.02.2017.
 */
public class ServiceException extends Exception {
    private Exception exception;

    public ServiceException(Exception exception) {
        this.exception = exception;
    }

    public ServiceException(String s) {
        super(s);
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
