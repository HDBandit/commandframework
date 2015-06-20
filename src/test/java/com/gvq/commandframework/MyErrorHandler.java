package com.gvq.commandframework;

import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.Command;
import com.gvq.commandframework.model.CommandParams;
import com.gvq.commandframework.model.ErrorHandler;

/**
 * Created by gerard on 16/6/15.
 */
public class MyErrorHandler implements ErrorHandler<ExecutionCommandException> {

    public void resolve(ExecutionCommandException commandException, Command command, CommandParams commandParams) throws ExecutionCommandException {

    }
}
