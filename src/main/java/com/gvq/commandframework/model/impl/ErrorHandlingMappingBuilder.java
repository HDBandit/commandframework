package com.gvq.commandframework.model.impl;

import com.gvq.commandframework.exception.CommandException;
import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.ErrorHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gerard on 14/6/15.
 */
public class ErrorHandlingMappingBuilder {

    private Map<Class<? extends ExecutionCommandException>, ErrorHandler> errorMapping = new HashMap<Class<? extends ExecutionCommandException>, ErrorHandler>();

    public static ErrorHandlingMappingBuilder newBuilder() {
        return new ErrorHandlingMappingBuilder();
    }

    private ErrorHandlingMappingBuilder() {

    }

    public ErrorHandlingMappingBuilder addErrorHandler(ErrorHandler errorHandler) {
        errorMapping.put(errorType, errorHandler);
        return this;
    }

    public ErrorHandlingMapping build() {
        ErrorHandlingMapping errorHandlingMapping = new ErrorHandlingMapping();
        errorHandlingMapping.setErrorMapping(errorMapping);
        return errorHandlingMapping;
    }
}
