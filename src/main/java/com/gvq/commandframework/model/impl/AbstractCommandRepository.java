package com.gvq.commandframework.model.impl;

import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.CommandRepository;

/**
 * Created by gerard on 16/6/15.
 */
public abstract class AbstractCommandRepository<T> implements CommandRepository<T> {

    public void execute(T commandkey) throws ExecutionCommandException {
        execute(commandkey, HashCommandParamsBuilder.newBuilder().build());
    }
}
