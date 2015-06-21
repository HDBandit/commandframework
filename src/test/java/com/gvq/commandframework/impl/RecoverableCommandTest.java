package com.gvq.commandframework.impl;

import com.gvq.commandframework.exception.ErrorHandlerCommandException;
import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.Command;
import com.gvq.commandframework.model.CommandParams;
import com.gvq.commandframework.model.ErrorHandler;
import com.gvq.commandframework.model.impl.ErrorHandlingMapping;
import com.gvq.commandframework.model.impl.HashCommandParamsBuilder;
import com.gvq.commandframework.model.impl.RecoverableCommand;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gerard on 21/6/15.
 */
public class RecoverableCommandTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_when_creating_a_recoverable_command_retries_number_is_invalid_then_exception_is_thrown(){
        RecoverableCommand recoverableCommand = new RecoverableCommand();
        recoverableCommand.setRetries(-1);
    }

    @Test(expected = NullPointerException.class)
    public void test_when_creating_a_recoverable_command_errorHandler_is_null_then_exception_is_thrown(){
        RecoverableCommand recoverableCommand = new RecoverableCommand();
        recoverableCommand.setErrorHandler(null);
    }

    @Test
    public void test_when_an_exception_occurs_then_recovery_action_works_properly() throws ExecutionCommandException {
        ErrorHandler errorHandler = Mockito.mock(ErrorHandler.class);
        Map<Class<? extends ExecutionCommandException>, ErrorHandler> errorMapping = new HashMap<Class<? extends ExecutionCommandException>, ErrorHandler>();
        errorMapping.put(ExecutionCommandException.class, errorHandler);

        ErrorHandlingMapping errorHandlingMapping = new ErrorHandlingMapping();
        errorHandlingMapping.setErrorMapping(errorMapping);

        Command command = Mockito.mock(Command.class);
        CommandParams commandParams = HashCommandParamsBuilder.newBuilder().build();

        ExecutionCommandException executionCommandException = new ExecutionCommandException();

        Mockito.doThrow(executionCommandException).doNothing().when(command).execute(commandParams);

        RecoverableCommand recoverableCommand = new RecoverableCommand();
        recoverableCommand.setRetries(3);
        recoverableCommand.setCommand(command);
        recoverableCommand.setErrorHandler(errorHandlingMapping);
        recoverableCommand.execute(commandParams);

        Mockito.verify(errorHandler, Mockito.times(1)).resolve(executionCommandException, recoverableCommand, commandParams);
        Mockito.verify(command, Mockito.times(2)).execute(commandParams);
    }

    @Test(expected = ExecutionCommandException.class)
    public void test_when_an_exception_occurs_then_recovery_action_is_not_enough() throws ExecutionCommandException {
        ErrorHandler errorHandler = Mockito.mock(ErrorHandler.class);
        Map<Class<? extends ExecutionCommandException>, ErrorHandler> errorMapping = new HashMap<Class<? extends ExecutionCommandException>, ErrorHandler>();
        errorMapping.put(ExecutionCommandException.class, errorHandler);

        ErrorHandlingMapping errorHandlingMapping = new ErrorHandlingMapping();
        errorHandlingMapping.setErrorMapping(errorMapping);

        Command command = Mockito.mock(Command.class);
        CommandParams commandParams = HashCommandParamsBuilder.newBuilder().build();

        ExecutionCommandException executionCommandException = new ExecutionCommandException();

        Mockito.doThrow(executionCommandException).when(command).execute(commandParams);

        RecoverableCommand recoverableCommand = new RecoverableCommand();
        try {
            recoverableCommand.setRetries(3);
            recoverableCommand.setCommand(command);
            recoverableCommand.setErrorHandler(errorHandlingMapping);
            recoverableCommand.execute(commandParams);
        }catch (ExecutionCommandException e) {
            Mockito.verify(errorHandler, Mockito.times(2)).resolve(executionCommandException, recoverableCommand, commandParams);
            Mockito.verify(command, Mockito.times(3)).execute(commandParams);
            throw e;
        }
    }

    @Test(expected = ErrorHandlerCommandException.class)
    public void test_when_an_exception_occurs_during_error_recovery_action_then_exception_is_thrown() throws ExecutionCommandException {
        ErrorHandler errorHandler = Mockito.mock(ErrorHandler.class);
        Map<Class<? extends ExecutionCommandException>, ErrorHandler> errorMapping = new HashMap<Class<? extends ExecutionCommandException>, ErrorHandler>();
        errorMapping.put(ExecutionCommandException.class, errorHandler);

        ErrorHandlingMapping errorHandlingMapping = new ErrorHandlingMapping();
        errorHandlingMapping.setErrorMapping(errorMapping);

        Command command = Mockito.mock(Command.class);
        CommandParams commandParams = HashCommandParamsBuilder.newBuilder().build();
        ExecutionCommandException executionCommandException = new ExecutionCommandException();
        Mockito.doThrow(executionCommandException).when(command).execute(commandParams);
        RecoverableCommand recoverableCommand = new RecoverableCommand();
        Mockito.doThrow(ErrorHandlerCommandException.class).when(errorHandler).resolve(executionCommandException, recoverableCommand, commandParams);
        try {
            recoverableCommand.setRetries(3);
            recoverableCommand.setCommand(command);
            recoverableCommand.setErrorHandler(errorHandlingMapping);
            recoverableCommand.execute(commandParams);
        }catch (ErrorHandlerCommandException e) {
            Mockito.verify(errorHandler, Mockito.times(1)).resolve(executionCommandException, recoverableCommand, commandParams);
            Mockito.verify(command, Mockito.times(1)).execute(commandParams);
            throw e;
        }
    }
}
