package com.hdbandit.commandframework.model.impl;

import com.hdbandit.commandframework.exception.ExecutionCommandException;
import com.hdbandit.commandframework.model.Command;
import com.hdbandit.commandframework.model.CommandParams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * Created by gerard on 19/6/15.
 */
public class ParallelCommand extends MultiCommand {

    private ExecutorService executorService;

    public void execute(final CommandParams commandParams) throws ExecutionCommandException {
        List<FutureTask> futureTasks = new ArrayList<FutureTask>();
        List<Command> commands = getCommands();

        for(final Command command : commands) {
            FutureTask<Command> futureTask = new FutureTask<Command>(new Callable<Command>() {
                public Command call() throws Exception {
                    command.execute(commandParams);
                    return command;
                }
            });
            futureTasks.add(futureTask);
            getExecutorService().execute(futureTask);
        }

        int i = 0;
        for (FutureTask futureTask: futureTasks) {
            try {
                futureTask.get();
                i++;
            } catch (InterruptedException e) {
                throw new ExecutionCommandException("Unexpected error", e);
            } catch (ExecutionException e) {
                throw new ExecutionCommandException(String.format("Error running command: %s", commands.get(i)), e);
            }
        }
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        if (executorService == null) {
            throw new NullPointerException("Executor service cannot be null");
        }
        this.executorService = executorService;
    }
}
