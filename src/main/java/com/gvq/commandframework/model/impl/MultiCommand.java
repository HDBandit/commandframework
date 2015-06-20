package com.gvq.commandframework.model.impl;

import com.gvq.commandframework.model.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerard on 15/6/15.
 */
public abstract class MultiCommand implements Command {

    protected List<Command> commands = new ArrayList<Command>();

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
