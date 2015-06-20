package com.gvq.commandframework.exception;

/**
 * Created by gerard on 13/6/15.
 */
public class CommandException extends Exception {

    public CommandException() {
        super();
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CommandException(Throwable throwable) {
        super(throwable);
    }

}
