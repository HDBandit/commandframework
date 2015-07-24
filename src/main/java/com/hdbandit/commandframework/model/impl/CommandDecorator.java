package com.hdbandit.commandframework.model.impl;

import com.hdbandit.commandframework.model.Command;

/**
 * Created by gerard on 16/6/15.
 */
public abstract class CommandDecorator implements Command {

    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        if (command == null) {
            throw new NullPointerException("Command cannot be null");
        }
        this.command = command;
    }
}
