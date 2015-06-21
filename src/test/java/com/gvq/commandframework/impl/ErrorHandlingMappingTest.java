package com.gvq.commandframework.impl;

import com.gvq.commandframework.exception.ErrorHandlerCommandException;
import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.Command;
import com.gvq.commandframework.model.CommandParams;
import com.gvq.commandframework.model.ErrorHandler;
import com.gvq.commandframework.model.impl.ErrorHandlingMapping;
import com.gvq.commandframework.model.impl.HashCommandParamsBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gerard on 21/6/15.
 */
public class ErrorHandlingMappingTest {

    @Test(expected = NullPointerException.class)
    public void test_when_error_mapping_is_null_then_exception_is_thrown(){
        ErrorHandlingMapping errorHandlingMapping = new ErrorHandlingMapping();
        errorHandlingMapping.setErrorMapping(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_when_execution_command_exception_is_null_then_exception_is_thrown() throws ExecutionCommandException {
        ErrorHandlingMapping errorHandlingMapping = new ErrorHandlingMapping();
        errorHandlingMapping.resolve(null, new Command() {
            public void execute(CommandParams commandParams) throws ExecutionCommandException {

            }
        }, HashCommandParamsBuilder.newBuilder().build());
    }

    @Test(expected = NullPointerException.class)
    public void test_when_command_is_null_then_exception_is_thrown() throws ExecutionCommandException {
        ErrorHandlingMapping errorHandlingMapping = new ErrorHandlingMapping();
        errorHandlingMapping.resolve(new ExecutionCommandException(), null, HashCommandParamsBuilder.newBuilder().build());
    }

    @Test(expected = NullPointerException.class)
    public void test_when_commandParams_is_null_then_exception_is_thrown() throws ExecutionCommandException {
        ErrorHandlingMapping errorHandlingMapping = new ErrorHandlingMapping();
        errorHandlingMapping.resolve(new ExecutionCommandException(), new Command() {
            public void execute(CommandParams commandParams) throws ExecutionCommandException {

            }
        }, null);
    }

    @Test(expected = ErrorHandlerCommandException.class)
    public void test_when_error_mapping_not_contains_error_then_exception_is_thrown() throws ExecutionCommandException {
        ErrorHandlingMapping errorHandlingMapping  = new ErrorHandlingMapping();
        errorHandlingMapping.setErrorMapping(new HashMap<Class<? extends ExecutionCommandException>, ErrorHandler>());
        errorHandlingMapping.resolve(new ExecutionCommandException(), new Command() {
            public void execute(CommandParams commandParams) throws ExecutionCommandException {

            }
        }, HashCommandParamsBuilder.newBuilder().build());
    }

    @Test
    public void test_when_error_mapping_contains_error_then_resolve_method_is_called() throws ExecutionCommandException {
        Map<Class<? extends ExecutionCommandException>, ErrorHandler> errorMapping = new HashMap<Class<? extends ExecutionCommandException>, ErrorHandler>();
        ErrorHandler errorHandler = Mockito.mock(ErrorHandler.class);
        errorMapping.put(ExecutionCommandException.class, errorHandler);

        ErrorHandlingMapping errorHandlingMapping = new ErrorHandlingMapping();
        errorHandlingMapping.setErrorMapping(errorMapping);

        ExecutionCommandException executionCommandException = new ExecutionCommandException();
        Command command = new Command() {
            public void execute(CommandParams commandParams) throws ExecutionCommandException {

            }
        };
        CommandParams commandParams = HashCommandParamsBuilder.newBuilder().build();

        errorHandlingMapping.resolve(executionCommandException, command, commandParams);

        Mockito.verify(errorHandler, Mockito.times(1)).resolve(executionCommandException, command, commandParams);
    }
}
