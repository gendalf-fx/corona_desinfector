package com.gendalf.app.annotatoin.config.proxy.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.gendalf.app.annotatoin.config.proxy.ProxyConfigurator;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

public class DeprecatedAnnotationProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithProxy(Object object, Class implClass) {
        if (implClass.isAnnotationPresent(Deprecated.class)) {
            if (implClass.getInterfaces().length == 0) {
                return Enhancer.create(implClass, (InvocationHandler) (proxy, method, args) -> {
                    return getInvocationHandlerLogic(object, method, args, "cglib");
                });
            } else {
                return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(),
                    (proxy, method, args) -> {
                        return getInvocationHandlerLogic(object, method, args, "dynamic proxy with interfaces");
                    });
            }
        } else {
            return object;
        }
    }

    private Object getInvocationHandlerLogic(Object object, Method method, Object[] args, String creator)
        throws IllegalAccessException, InvocationTargetException {
        System.out.println("What are you doing BASTARD!!??  " + creator);
        return method.invoke(object, args);
    }
}
