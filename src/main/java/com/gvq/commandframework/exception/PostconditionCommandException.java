package com.gvq.commandframework.exception;

/**
 * Created by gerard on 13/6/15.
 */
public class PostconditionCommandException extends ExecutionCommandException {

    public PostconditionCommandException() {
        super();
    }

    public PostconditionCommandException(String message) {
        super(message);
    }

    public PostconditionCommandException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PostconditionCommandException(Throwable throwable) {
        super(throwable);
    }
}
