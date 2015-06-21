package com.gvq.commandframework.model.impl;

import com.gvq.commandframework.model.CommandParams;

import java.util.Map;

/**
 * Created by gerard on 13/6/15.
 */
public class HashCommandParams implements CommandParams {

    private Map<String, Object> params;

    public HashCommandParams(Map<String, Object> params) {
        setParams(params);
    }

    public Object getParam(String name) {
        if (!isParamAvailable(name)) {
            throw new IllegalArgumentException(String.format("Param with name: '%s' doesn't exist", name));
        }

        return params.get(name);
    }

    public <T> T getParam(String name, Class<T> type) {
        return (T) getParam(name);
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        if (params == null) {
            throw new NullPointerException("Params cannot be null");
        }
        this.params = params;
    }

    public boolean isParamAvailable(String name) {
        if (name == null) {
            throw new NullPointerException("Param name cannot be null");
        }

        if (params.containsKey(name)) {
            return true;
        }

        return false;
    }

    public int size() {
        return params.size();
    }

    @Override
    public String toString() {
        return this.params.toString();
    }
}
