package com.gvq.commandframework;

import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.Command;
import com.gvq.commandframework.model.CommandParams;

/**
 * Created by gerard on 14/6/15.
 */
public class HomeCommand implements Command {

    public void execute(CommandParams commandParams) throws ExecutionCommandException {
        System.out.println(String.format("Home command with params : %s", commandParams));
    }
}
