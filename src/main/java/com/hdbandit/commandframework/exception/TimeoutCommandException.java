package com.hdbandit.commandframework.exception;

/**
 * Created by gerard on 13/6/15.
 */
public class TimeoutCommandException extends ExecutionCommandException {

    public TimeoutCommandException() {
        super();
    }

    public TimeoutCommandException(String message) {
        super(message);
    }

    public TimeoutCommandException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public TimeoutCommandException(Throwable throwable) {
        super(throwable);
    }
}
