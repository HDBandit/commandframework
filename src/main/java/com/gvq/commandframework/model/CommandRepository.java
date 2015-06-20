package com.gvq.commandframework.model;

import com.gvq.commandframework.exception.ExecutionCommandException;

/**
 * Created by gerard on 13/6/15.
 */
public interface CommandRepository<T> {

    void execute(T commandkey) throws ExecutionCommandException;

    void execute(T commandkey, CommandParams commandParams) throws ExecutionCommandException;

}
