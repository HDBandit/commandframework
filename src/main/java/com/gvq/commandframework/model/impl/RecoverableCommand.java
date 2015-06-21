package com.gvq.commandframework.model.impl;

import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.Command;
import com.gvq.commandframework.model.CommandParams;
import com.gvq.commandframework.model.ErrorHandler;

/**
 * Created by gerard on 14/6/15.
 */
public class RecoverableCommand extends CommandDecorator {

    private ErrorHandler errorHandler;
    private int retries;

    public void execute(CommandParams commandParams) throws ExecutionCommandException {
        int availableRetries = retries;
        boolean isDone = false;
        do {
            isDone = false;
            try {
                getCommand().execute(commandParams);
                isDone = true;
            } catch (ExecutionCommandException e) {
                availableRetries--;
                if (availableRetries > 0) {
                    errorHandler.resolve(e, this, commandParams);
                } else {
                    throw e;
                }
            }
        } while (!isDone && availableRetries > 0);
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        if (errorHandler == null) {
            throw new NullPointerException("Error handler cannot be null");
        }
        this.errorHandler = errorHandler;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        if (retries < 0) {
            throw new IllegalArgumentException(String.format("Invalid retries number %d. Retries must be 0 or greater", retries));
        }
        this.retries = retries;
    }

}
