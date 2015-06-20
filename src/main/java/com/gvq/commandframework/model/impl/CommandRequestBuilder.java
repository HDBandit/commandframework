package com.gvq.commandframework.model.impl;

import com.gvq.commandframework.model.CommandParams;

/**
 * Created by gerard on 16/6/15.
 */
public class CommandRequestBuilder {

    private Object commandKey;
    private CommandParams commandParams;

    public static CommandRequestBuilder newBuilder() {
        return new CommandRequestBuilder();
    }

    private CommandRequestBuilder() {

    }

    public CommandRequestBuilder setCommandKey(Object commandKey) {
        this.commandKey = commandKey;
        return this;
    }

    public CommandRequestBuilder setCommandParams(CommandParams commandParams) {
        this.commandParams = commandParams;
        return this;
    }

    public CommandRequest build() {
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setCommandKey(this.commandKey);
        commandRequest.setCommandParams(this.commandParams);
        return commandRequest;
    }

}
