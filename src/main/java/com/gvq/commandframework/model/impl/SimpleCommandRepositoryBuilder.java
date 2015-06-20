package com.gvq.commandframework.model.impl;

/**
 * Created by gerard on 18/6/15.
 */
public class SimpleCommandRepositoryBuilder<T> {

    public static SimpleCommandRepositoryBuilder newBuilder() {
        return new SimpleCommandRepositoryBuilder();
    }

    private SimpleCommandRepositoryBuilder() {

    }


    public SimpleCommandRepository<T>  build(<T> T) {
        SimpleCommandRepository<T> simpleCommandRepository = new SimpleCommandRepository<T>();
        return null;
    }
}
