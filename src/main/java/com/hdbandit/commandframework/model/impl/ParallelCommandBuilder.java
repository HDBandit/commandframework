package com.hdbandit.commandframework.model.impl;

import com.hdbandit.commandframework.model.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerard on 15/6/15.
 */
public class ParallelCommandBuilder {

    private List<Command> commands = new ArrayList<Command>();

    public static ParallelCommandBuilder newBuilder() {
        return new ParallelCommandBuilder();
    }

    private ParallelCommandBuilder() {

    }

    public ParallelCommandBuilder addCommand(Command command) {
        if (command == null) {
            throw new NullPointerException("Command cannot be null");
        }

        this.commands.add(command);
        return this;
    }

    public ParallelCommand build() {
        ParallelCommand parallelCommand = new ParallelCommand();
        parallelCommand.setCommands(commands);
        return parallelCommand;
    }
}
