package com.gendalf.app.config;

import java.util.ArrayList;
import java.util.List;

import com.gendalf.app.annotatoin.config.ObjectConfigurator;
import com.gendalf.app.context.ApplicationContext;
import lombok.Setter;
import lombok.SneakyThrows;

public class ObjectFactory {
    private final ApplicationContext context;

    private final List<ObjectConfigurator> configurators;

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        this.configurators = new ArrayList<>();
        for (Class<? extends ObjectConfigurator> aClass :
            context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        T t = implClass.getDeclaredConstructor().newInstance();
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
        return t;
    }
}
