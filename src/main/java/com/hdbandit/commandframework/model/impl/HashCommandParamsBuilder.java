package com.hdbandit.commandframework.model.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gerard on 13/6/15.
 */
public class HashCommandParamsBuilder {

    private Map<String, Object> params = new HashMap<String, Object>();

    public static HashCommandParamsBuilder newBuilder() {
        return new HashCommandParamsBuilder();
    }

    private HashCommandParamsBuilder() {

    }

    public HashCommandParamsBuilder addParam(String name, Object value) {
        if (name == null) {
            throw new NullPointerException("Param name cannot be null");
        }

        if (value == null) {
            throw new NullPointerException("Param value cannot be null");
        }

        if (params.containsKey(name)) {
            throw new IllegalArgumentException(String.format("Param with name: '%s' already exist", name));
        }

        params.put(name, value);
        return this;
    }

    public HashCommandParams build() {
        return new HashCommandParams(params);
    }

}
