package com.gvq.commandframework.impl;

import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.Command;
import com.gvq.commandframework.model.CommandParams;
import com.gvq.commandframework.model.impl.HashCommandParamsBuilder;
import com.gvq.commandframework.model.impl.ParallelCommand;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.Executors;

import static org.mockito.Mockito.*;

/**
 * Created by gerard on 20/6/15.
 */
public class ParallelCommandTest {

    @Test(expected = NullPointerException.class)
    public void test_when_try_to_add_null_commands_then_an_exception_is_thrown() {
        ParallelCommand parallelCommand = new ParallelCommand();
        parallelCommand.setCommands(null);
    }

    @Test
    public void test_when_execute_parallel_command_then_execution_is_ok() throws ExecutionCommandException {
        Command command1 = mock(Command.class);
        Command command2 = mock(Command.class);
        Command command3 = mock(Command.class);
        Command command4 = mock(Command.class);

        CommandParams commandParams = HashCommandParamsBuilder.newBuilder().build();
        ParallelCommand sut = new ParallelCommand();
        sut.setExecutorService(Executors.newFixedThreadPool(5));
        sut.setCommands(Arrays.asList(command3, command4, command1, command2));
        sut.execute(commandParams);

        verify(command1, times(1)).execute(commandParams);
        verify(command2, times(1)).execute(commandParams);
        verify(command3, times(1)).execute(commandParams);
        verify(command4, times(1)).execute(commandParams);
    }

    @Test(expected = ExecutionCommandException.class)
    public void test_when_execute_parallel_command_that_throws_an_exception_then_exception_is_propagated() throws ExecutionCommandException {
        Command command1 = new Command() {
            public void execute(CommandParams commandParams) throws ExecutionCommandException {

            }
        };
        Command command2 = new Command() {
            public void execute(CommandParams commandParams) throws ExecutionCommandException {
                throw new ExecutionCommandException();
            }
        };

        CommandParams commandParams = HashCommandParamsBuilder.newBuilder().build();
        ParallelCommand sut = new ParallelCommand();
        sut.setExecutorService(Executors.newFixedThreadPool(5));
        sut.setCommands(Arrays.asList(command1, command2));
        sut.execute(commandParams);
    }

}
