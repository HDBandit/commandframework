package com.hdbandit.commandframework.model;

import com.hdbandit.commandframework.exception.ExecutionCommandException;

/**
 * Created by gerard on 13/6/15.
 */
public interface Command {

    void execute(CommandParams commandParams) throws ExecutionCommandException;
}
