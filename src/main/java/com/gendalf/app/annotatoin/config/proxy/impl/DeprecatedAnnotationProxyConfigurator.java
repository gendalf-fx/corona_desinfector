package com.gendalf.app.annotatoin.config.proxy.impl;

import java.lang.reflect.Proxy;

import com.gendalf.app.annotatoin.config.proxy.ProxyConfigurator;

public class DeprecatedAnnotationProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithProxy(Object object, Class implClass) {
        if (implClass.isAnnotationPresent(Deprecated.class)) {
            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("What are you doing BASTARD!!??");
                    return method.invoke(object);
                });
        } else {
            return object;
        }
    }
}
