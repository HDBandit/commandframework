package com.hdbandit.commandframework.impl;

import com.hdbandit.commandframework.exception.ErrorHandlerCommandException;
import com.hdbandit.commandframework.exception.ExecutionCommandException;
import com.hdbandit.commandframework.exception.TimeoutCommandException;
import com.hdbandit.commandframework.model.Command;
import com.hdbandit.commandframework.model.CommandParams;
import com.hdbandit.commandframework.model.ErrorHandler;
import com.hdbandit.commandframework.model.impl.ErrorHandlingMapping;
import com.hdbandit.commandframework.model.impl.ErrorHandlingMappingBuilder;
import com.hdbandit.commandframework.model.impl.HashCommandParamsBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gerard on 24/6/15.
 */
public class ErrorHandlingMappingBuilderTest {

    private class MyErrorHandler implements ErrorHandler<ExecutionCommandException> {
        public void resolve(ExecutionCommandException commandException, Command command, CommandParams commandParams) throws ExecutionCommandException {

        }
    }

    private class MyErrorHandler2 implements ErrorHandler<TimeoutCommandException> {
        public void resolve(TimeoutCommandException commandException, Command command, CommandParams commandParams) throws ExecutionCommandException {

        }
    }

    private class MyErrorHandler3 implements ErrorHandler<ErrorHandlerCommandException> {
        public void resolve(ErrorHandlerCommandException commandException, Command command, CommandParams commandParams) throws ExecutionCommandException {

        }
    }

    private class MyExecutionCommandException extends ExecutionCommandException {

    }

    @Test(expected = NullPointerException.class)
    public void test_when_adding_null_error_handler_then_exception_is_thrown(){
        ErrorHandlingMappingBuilder.newBuilder().addErrorHandler(null).build();
    }

    @Test
    public void test_when() throws ExecutionCommandException {
        ErrorHandlingMapping errorHandlingMapping = ErrorHandlingMappingBuilder.newBuilder()
                .addErrorHandler(new MyErrorHandler())
                .addErrorHandler(new MyErrorHandler2())
                .addErrorHandler(new MyErrorHandler3())
                .build();
        ExecutionCommandException exception = new ExecutionCommandException();
        errorHandlingMapping.resolve(exception, new Command() {
            public void execute(CommandParams commandParams) throws ExecutionCommandException {

            }
        }, HashCommandParamsBuilder.newBuilder().build());

        Assert.assertTrue(errorHandlingMapping.getErrorMapping().size() == 3);
        Assert.assertTrue(errorHandlingMapping.getErrorMapping().containsKey(ExecutionCommandException.class));
        Assert.assertTrue(errorHandlingMapping.getErrorMapping().containsKey(TimeoutCommandException.class));
        Assert.assertTrue(errorHandlingMapping.getErrorMapping().containsKey(ErrorHandlerCommandException.class));
        Assert.assertFalse(errorHandlingMapping.getErrorMapping().containsKey(MyExecutionCommandException.class));
    }
}
