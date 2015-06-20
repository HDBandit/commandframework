package com.gvq.commandframework.exception;

/**
 * Created by gerard on 13/6/15.
 */
public class PreconditionCommandException extends ExecutionCommandException {

    public PreconditionCommandException() {
        super();
    }

    public PreconditionCommandException(String message) {
        super(message);
    }

    public PreconditionCommandException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PreconditionCommandException(Throwable throwable) {
        super(throwable);
    }
}
