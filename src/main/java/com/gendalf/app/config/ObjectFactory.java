package com.gendalf.app.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gendalf.app.servcie.policeman.AngerPoliceman;
import com.gendalf.app.servcie.policeman.Policeman;
import lombok.SneakyThrows;

public class ObjectFactory {
    private static ObjectFactory instance = new ObjectFactory();

    private List<ObjectConfigurator> configurators;

    private Config config;

    @SneakyThrows
    public ObjectFactory() {
        this.config = new JavaConfig("com.gendalf.app", new HashMap<>(Map.of(Policeman.class, AngerPoliceman.class)));
        this.configurators = new ArrayList<>();
        for (Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
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
        T t = implClass.getDeclaredConstructor().newInstance();
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t));
        return t;
    }
}
