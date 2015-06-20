package com.gvq.commandframework.model.impl;

import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.Command;
import com.gvq.commandframework.model.CommandParams;
import com.gvq.commandframework.model.CommandRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gerard on 13/6/15.
 */
public class SimpleCommandRepository<T> implements CommandRepository<T> {

    private Map<T, Command> commandsMapping = new HashMap<T, Command>();

    public void addCommand(T commandkey, Command command) {
        if (commandkey == null) {
            throw new NullPointerException("Command key cannot be null");
        }

        if (command == null) {
            throw new NullPointerException("Command cannot be null");
        }

        if (isCommandAvailable(commandkey)) {
            throw new IllegalArgumentException(String.format("Command key %s is already exists", commandkey));
        }

        commandsMapping.put(commandkey, command);
    }

    private boolean isCommandAvailable(T commandkey) {
        assert commandkey != null;

        if (commandsMapping.containsKey(commandkey)) {
           return true;
        }

        return false;
    }

    public void execute(T commandkey) throws ExecutionCommandException {
        execute(commandkey, HashCommandParamsBuilder.newBuilder().build());
    }

    public void execute(T commandkey, CommandParams commandParams) throws ExecutionCommandException {
        if (commandkey == null) {
            throw new NullPointerException("Command key cannot be null");
        }

        if (!isCommandAvailable(commandkey)) {
            throw new IllegalArgumentException(String.format("Command key %s doesn't exist", commandkey));
        }

        if (commandParams == null) {
            throw new NullPointerException("Command params cannot be null");
        }

        commandsMapping.get(commandkey).execute(commandParams);
    }

    public CommandRepository<T> getCommandRepository() {
        return this;
    }

    public Map<T, Command> getCommandsMapping() {
        return commandsMapping;
    }

    public void setCommandsMapping(Map<T, Command> commandsMapping) {
        if (commandsMapping == null) {
            throw new NullPointerException("Command mappings cannot be null");
        }
        this.commandsMapping = commandsMapping;
    }

    @Override
    public String toString() {
        return String.format("Available commands: %s", commandsMapping.keySet().toString());
    }
}
