package com.gvq.commandframework.model.impl;

import com.gvq.commandframework.exception.ErrorHandlerCommandException;
import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.Command;
import com.gvq.commandframework.model.CommandParams;
import com.gvq.commandframework.model.ErrorHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gerard on 14/6/15.
 */
public class ErrorHandlingMapping implements ErrorHandler {

    private Map<Class<? extends ExecutionCommandException>, ErrorHandler> errorMapping = new HashMap<Class<? extends ExecutionCommandException>, ErrorHandler>();

    public void resolve(ExecutionCommandException commandException, Command command, CommandParams commandParams) throws ExecutionCommandException {
        if (commandException == null) {
            throw new NullPointerException("Exception cannot be null");
        }

        if (command == null) {
            throw new NullPointerException("Command cannot be null");
        }

        if (commandParams == null) {
            throw new NullPointerException("Command params cannot be null");
        }

        if (!errorMapping.containsKey(commandException.getClass())) {
            throw new ErrorHandlerCommandException(String.format("No error handler found for %s", commandException));
        }

        errorMapping.get(commandException.getClass()).resolve(commandException, command, commandParams);
    }

    public Map<Class<? extends ExecutionCommandException>, ErrorHandler> getErrorMapping() {
        return errorMapping;
    }

    public void setErrorMapping(Map<Class<? extends ExecutionCommandException>, ErrorHandler> errorMapping) {
        if (errorMapping == null) {
            throw new NullPointerException("Error mapping cannot be null");
        }
        this.errorMapping = errorMapping;
    }
}
