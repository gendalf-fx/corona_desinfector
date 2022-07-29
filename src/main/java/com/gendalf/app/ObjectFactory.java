package com.gendalf.app;

import lombok.SneakyThrows;

public class ObjectFactory {
    private static ObjectFactory instance = new ObjectFactory();

    private Config config = new JavaConfig("com.gendalf.app");

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
