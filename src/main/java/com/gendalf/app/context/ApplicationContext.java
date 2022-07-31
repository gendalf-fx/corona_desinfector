package com.gendalf.app.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.gendalf.app.config.ObjectFactory;

public class ApplicationContext {
    private ObjectFactory factory;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();

    public <T> T getObject(Class<T> type) {
        return null;
    }
}
