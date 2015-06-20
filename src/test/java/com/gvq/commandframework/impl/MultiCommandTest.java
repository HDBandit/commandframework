package com.gvq.commandframework.impl;

import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.Command;
import com.gvq.commandframework.model.CommandParams;
import com.gvq.commandframework.model.impl.MultiCommand;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerard on 21/6/15.
 */
public class MultiCommandTest {

    private class MyMulticommand extends MultiCommand {

        public void execute(CommandParams commandParams) throws ExecutionCommandException {

        }
    }

    @Test(expected = NullPointerException.class)
    public void test_when_configure_commands_as_null_then_exception_is_thrown() {
        MyMulticommand myMulticommand = new MyMulticommand();
        myMulticommand.setCommands(null);
    }

    @Test
    public void test_when_multi_command_is_created_then_getCommands_method_returns_the_same_object() {
        List<Command> commands = new ArrayList<Command>();
        commands.add(new Command() {
            public void execute(CommandParams commandParams) throws ExecutionCommandException {

            }
        });
        MyMulticommand myMulticommand = new MyMulticommand();
        myMulticommand.setCommands(commands);
        Assert.assertEquals(commands, myMulticommand.getCommands());
        Assert.assertTrue(commands.size() == myMulticommand.getCommands().size());
    }
}
