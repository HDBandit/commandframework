package com.hdbandit.commandframework.model.impl;

import com.hdbandit.commandframework.exception.ExecutionCommandException;
import com.hdbandit.commandframework.model.Command;
import com.hdbandit.commandframework.model.CommandParams;

import java.util.List;

/**
 * Created by gerard on 15/6/15.
 */
public class CommandSequence extends MultiCommand {

    public void execute(CommandParams commandParams) throws ExecutionCommandException {
        List<Command> cmds = getCommands();
        for (Command command : cmds) {
            command.execute(commandParams);
        }
    }

}
