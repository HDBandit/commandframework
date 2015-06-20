package com.gvq.commandframework.model.impl;

import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.Command;
import com.gvq.commandframework.model.CommandParams;

/**
 * Created by gerard on 15/6/15.
 */
public class CommandSequence extends MultiCommand {

    public void execute(CommandParams commandParams) throws ExecutionCommandException {
        for (Command command : commands) {
            command.execute(commandParams);
        }
    }

}
