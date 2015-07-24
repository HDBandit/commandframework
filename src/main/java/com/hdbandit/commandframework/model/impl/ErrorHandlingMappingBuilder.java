package com.hdbandit.commandframework.model.impl;

import com.hdbandit.commandframework.exception.ExecutionCommandException;
import com.hdbandit.commandframework.model.ErrorHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
        Type[] types = errorHandler.getClass().getGenericInterfaces();
        String className = null;
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type rawType = parameterizedType.getRawType();

                if (ErrorHandler.class.getTypeName().equals(rawType.getTypeName())) {
                    className = parameterizedType.getActualTypeArguments()[0].getTypeName();
                    break;
                }
            }
        }

        try {
            errorMapping.put((Class<? extends ExecutionCommandException>)Class.forName(className), errorHandler);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Unexpected error", e);
        }
        return this;
    }

    public ErrorHandlingMapping build() {
        ErrorHandlingMapping errorHandlingMapping = new ErrorHandlingMapping();
        errorHandlingMapping.setErrorMapping(errorMapping);
        return errorHandlingMapping;
    }
}
