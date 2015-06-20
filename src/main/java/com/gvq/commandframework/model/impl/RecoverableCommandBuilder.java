package com.gvq.commandframework.model.impl;

import com.gvq.commandframework.model.Command;
import com.gvq.commandframework.model.ErrorHandler;

/**
 * Created by gerard on 16/6/15.
 */
public class RecoverableCommandBuilder {

    private Command command;
    private int retries;
    private ErrorHandler errorHandler;

    public static RecoverableCommandBuilder newBuilder() {
        return new RecoverableCommandBuilder();
    }

    private RecoverableCommandBuilder() {

    }

    public RecoverableCommandBuilder setCommand(Command command) {
        this.command = command;
        return this;
    }

    public RecoverableCommandBuilder setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }

    public RecoverableCommandBuilder setRetries(int retries) {
        this.retries = retries;
        return this;
    }

    public RecoverableCommand build() {
        RecoverableCommand recoverableCommand = new RecoverableCommand();
        recoverableCommand.setErrorHandler(errorHandler);
        recoverableCommand.setCommand(command);
        recoverableCommand.setRetries(retries);
        return recoverableCommand;
    }


}
