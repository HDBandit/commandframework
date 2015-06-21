package com.gvq.commandframework.impl;

import com.gvq.commandframework.model.CommandParams;
import com.gvq.commandframework.model.impl.CommandRequest;
import com.gvq.commandframework.model.impl.HashCommandParamsBuilder;
import org.junit.Test;

/**
 * Created by gerard on 21/6/15.
 */
public class CommandRequestTest {

    @Test(expected = NullPointerException.class)
    public void test_when_setting_commandParams_as_null_then_exception_is_thrown(){
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setCommandParams(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_when_setting_commandkey_as_null_then_exception_is_thrown(){
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setCommandKey(null);
    }

    @Test
    public void test_when_command_request_is_created_then_set_and_get_methods_work_properly(){
        CommandRequest commandRequest = new CommandRequest();
        String commandkey = "my command key";
        CommandParams commandParams = HashCommandParamsBuilder.newBuilder().build();
        commandRequest.setCommandKey("my command key");
        commandRequest.setCommandParams(commandParams);

        org.junit.Assert.assertEquals(commandkey, commandRequest.getCommandKey());
        org.junit.Assert.assertEquals(commandParams, commandRequest.getCommandParams());
    }

}
