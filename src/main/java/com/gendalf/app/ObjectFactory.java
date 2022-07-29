package com.gendalf.app;

import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;

public class ObjectFactory {
    private static ObjectFactory instance = new ObjectFactory();

    private Config config;

    public ObjectFactory() {
        this.config = new JavaConfig("com.gendalf.app", new HashMap<>(Map.of(Policeman.class, AngerPoliceman.class)));
    }

    public static ObjectFactory getInstance() {
        return instance;
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

       return implClass.getDeclaredConstructor().newInstance();
    }
}
