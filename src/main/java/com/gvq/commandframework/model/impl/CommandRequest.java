package com.gvq.commandframework.model.impl;

import com.gvq.commandframework.model.CommandParams;

/**
 * Created by gerard on 16/6/15.
 */
public class CommandRequest {

    private Object commandKey;
    private CommandParams commandParams;

    public Object getCommandKey() {
        return commandKey;
    }

    public void setCommandKey(Object commandKey) {
        if (commandKey == null) {
            throw new NullPointerException("Command key cannot be null");
        }
        this.commandKey = commandKey;
    }

    public CommandParams getCommandParams() {
        return commandParams;
    }

    public void setCommandParams(CommandParams commandParams) {
        if (commandParams == null) {
            throw new NullPointerException("Command params cannot be null");
        }
        this.commandParams = commandParams;
    }
}
