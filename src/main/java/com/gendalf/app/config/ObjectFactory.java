package com.gendalf.app.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import com.gendalf.app.annotatoin.config.initialiser.ObjectConfigurator;
import com.gendalf.app.annotatoin.config.proxy.ProxyConfigurator;
import com.gendalf.app.context.ApplicationContext;
import lombok.SneakyThrows;

public class ObjectFactory {
    private final ApplicationContext context;

    private final List<ObjectConfigurator> objectConfigurators;

    private final List<ProxyConfigurator> proxyConfigurators;

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;

        this.objectConfigurators = new ArrayList<>();
        for (Class<? extends ObjectConfigurator> oClass :
            context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            objectConfigurators.add(oClass.getDeclaredConstructor().newInstance());
        }

        this.proxyConfigurators = new ArrayList<>();
        for (Class<? extends ProxyConfigurator> aClass :
            context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
        }

    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        T newInstance = implClass.getDeclaredConstructor().newInstance();
        for (ObjectConfigurator objectConfigurator : objectConfigurators) {
           objectConfigurator.configure(newInstance, context);
        }

        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(newInstance);
            }
        }

        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            newInstance = (T) proxyConfigurator.replaceWithProxy(newInstance, implClass);
        }

        return newInstance;
    }
}
