package com.gvq.commandframework.model.impl;

import com.gvq.commandframework.model.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerard on 15/6/15.
 */
public class CommandSequenceBuilder {

    private List<Command> commands = new ArrayList<Command>();

    public static CommandSequenceBuilder newBuilder() {
        return new CommandSequenceBuilder();
    }

    private CommandSequenceBuilder() {

    }

    public CommandSequenceBuilder addCommand(Command command) {
        if (command == null) {
            throw new NullPointerException("Command cannot be null");
        }

        this.commands.add(command);
        return this;
    }

    public CommandSequence build() {
        CommandSequence commandSequence = new CommandSequence();
        commandSequence.setCommands(commands);
        return commandSequence;
    }
}
