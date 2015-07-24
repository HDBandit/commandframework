package com.hdbandit.commandframework.model;

/**
 * Created by gerard on 13/6/15.
 */
public interface CommandParams {

    Object getParam(String name);

    <T> T getParam(String name, Class<T> type);

    boolean isParamAvailable(String name);

    int size();
}
