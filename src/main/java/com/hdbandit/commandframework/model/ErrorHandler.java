package com.hdbandit.commandframework.model;

import com.hdbandit.commandframework.exception.ExecutionCommandException;

/**
 * Created by gerard on 14/6/15.
 */
public interface ErrorHandler<T extends ExecutionCommandException> {

    void resolve(T commandException, Command command, CommandParams commandParams) throws ExecutionCommandException;

}
