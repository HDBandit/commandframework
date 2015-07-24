package com.hdbandit.commandframework.model.impl;

import com.hdbandit.commandframework.model.Command;

import java.util.List;

/**
 * Created by gerard on 15/6/15.
 */
public abstract class MultiCommand implements Command {

    protected List<Command> commands;

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        if (commands == null) {
            throw new NullPointerException("Commands cannot be null");
        }

        this.commands = commands;
    }

}
