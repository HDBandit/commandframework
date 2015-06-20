package com.gvq.commandframework.impl;

import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.Command;
import com.gvq.commandframework.model.CommandParams;
import com.gvq.commandframework.model.impl.CommandSequence;
import com.gvq.commandframework.model.impl.HashCommandParamsBuilder;
import org.junit.Test;
import org.mockito.InOrder;
import static org.mockito.Mockito.*;

import java.util.Arrays;

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
    public void test_when_execute_command_sequence_then_the_order_execution_is_sequential() throws ExecutionCommandException {
        Command command1 = mock(Command.class);
        Command command2 = mock(Command.class);

        CommandParams commandParams = HashCommandParamsBuilder.newBuilder().build();
        CommandSequence sut = new CommandSequence();
        sut.setCommands(Arrays.asList(command1, command2));
        sut.execute(commandParams);
        sut.setCommands(Arrays.asList(command2, command1));
        sut.execute(commandParams);

        verify(command1, times(2)).execute(commandParams);
        verify(command2, times(2)).execute(commandParams);

        InOrder inOrder = inOrder(command1, command2);
        inOrder.verify(command1, times(2)).execute(commandParams);
        inOrder.verify(command2, times(2)).execute(commandParams);
        inOrder.verify(command2).execute(commandParams);
        inOrder.verify(command1).execute(commandParams);
    }

}
