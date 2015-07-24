package com.hdbandit.commandframework.exception;

/**
 * Created by gerard on 13/6/15.
 */
public class ExecutionCommandException extends CommandException {

    public ExecutionCommandException() {
        super();
    }

    public ExecutionCommandException(String message) {
        super(message);
    }

    public ExecutionCommandException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ExecutionCommandException(Throwable throwable) {
        super(throwable);
    }
}
