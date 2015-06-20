package com.gvq.commandframework.model.impl;

import com.gvq.commandframework.exception.ExecutionCommandException;
import com.gvq.commandframework.model.CommandParams;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by gerard on 16/6/15.
 */
public class BlockingQueueCommandRepository<T> extends CommandRepositoryDecorator<T> {

    private BlockingQueue<CommandRequest> blockingQueue;
    private long timeout;

    public void execute(T commandkey, CommandParams commandParams) throws ExecutionCommandException {
        try {
            getBlockingQueue().offer(CommandRequestBuilder.newBuilder().setCommandKey(commandkey).setCommandParams(commandParams).build(), getTimeout(), TimeUnit.MILLISECONDS);
            CommandRequest commandRequest = getBlockingQueue().peek();
            getCommandRepository().execute((T)commandRequest.getCommandKey(), commandRequest.getCommandParams());
            getBlockingQueue().poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public BlockingQueue<CommandRequest> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(BlockingQueue<CommandRequest> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
