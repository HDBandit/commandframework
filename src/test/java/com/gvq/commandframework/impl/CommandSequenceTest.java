package com.gvq.commandframework.impl;

import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.Command;
import com.gvq.commandframework.model.CommandParams;
import com.gvq.commandframework.model.impl.CommandSequence;
import com.gvq.commandframework.model.impl.HashCommandParamsBuilder;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.exceptions.verification.VerificationInOrderFailure;

import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * Created by gerard on 20/6/15.
 */
public class CommandSequenceTest {

    @Test(expected = NullPointerException.class)
    public void test_when_try_to_add_null_commands_then_an_exception_is_thrown() {
        CommandSequence commandSequence = new CommandSequence();
        commandSequence.setCommands(null);
    }

    @Test
    public void test_when_execute_command_sequence_then_order_execution_is_ok() throws ExecutionCommandException {
        Command command1 = mock(Command.class);
        Command command2 = mock(Command.class);
        Command command3 = mock(Command.class);
        Command command4 = mock(Command.class);

        CommandParams commandParams = HashCommandParamsBuilder.newBuilder().build();
        CommandSequence sut = new CommandSequence();
        sut.setCommands(Arrays.asList(command3, command4, command1, command2));
        sut.execute(commandParams);

        InOrder inOrder = inOrder(command1, command2, command3, command4);
        inOrder.verify(command3, times(1)).execute(commandParams);
        inOrder.verify(command4, times(1)).execute(commandParams);
        inOrder.verify(command1, times(1)).execute(commandParams);
        inOrder.verify(command2, times(1)).execute(commandParams);
    }

    @Test(expected = VerificationInOrderFailure.class)
    public void test_when_execute_command_sequence_then_order_execution_is_ko() throws ExecutionCommandException {
        Command command1 = mock(Command.class);
        Command command2 = mock(Command.class);
        Command command3 = mock(Command.class);
        Command command4 = mock(Command.class);

        CommandParams commandParams = HashCommandParamsBuilder.newBuilder().build();
        CommandSequence sut = new CommandSequence();
        sut.setCommands(Arrays.asList(command3, command4, command1, command2));
        sut.execute(commandParams);

        InOrder inOrder = inOrder(command1, command2, command3, command4);
        inOrder.verify(command1, times(1)).execute(commandParams);
        inOrder.verify(command4, times(1)).execute(commandParams);
        inOrder.verify(command1, times(1)).execute(commandParams);
        inOrder.verify(command2, times(1)).execute(commandParams);
    }

    @Test(expected = ExecutionCommandException.class)
    public void test_when_execute_command_sequence_that_throws_an_exception_then_exception_is_propagated() throws ExecutionCommandException {
        Command command1 = mock(Command.class);
        Command command2 = mock(Command.class);
        Command command3 = mock(Command.class);
        Command command4 = mock(Command.class);

        CommandParams commandParams = HashCommandParamsBuilder.newBuilder().build();
        try {
            doThrow(ExecutionCommandException.class).when(command3).execute(commandParams);

            CommandSequence sut = new CommandSequence();
            sut.setCommands(Arrays.asList(command4, command3, command1, command2));
            sut.execute(commandParams);
        } catch (ExecutionCommandException e) {
            InOrder inOrder = inOrder(command1, command2, command3, command4);
            inOrder.verify(command4, times(1)).execute(commandParams);
            inOrder.verify(command3, times(1)).execute(commandParams);
            inOrder.verify(command1, times(0)).execute(commandParams);
            inOrder.verify(command2, times(0)).execute(commandParams);

            throw e;
        }

    }

}
