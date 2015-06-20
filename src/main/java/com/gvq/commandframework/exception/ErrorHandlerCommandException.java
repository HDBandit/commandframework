package com.gvq.commandframework.exception;

/**
 * Created by gerard on 13/6/15.
 */
public class ErrorHandlerCommandException extends ExecutionCommandException {

    public ErrorHandlerCommandException() {
        super();
    }

    public ErrorHandlerCommandException(String message) {
        super(message);
    }

    public ErrorHandlerCommandException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ErrorHandlerCommandException(Throwable throwable) {
        super(throwable);
    }
}
