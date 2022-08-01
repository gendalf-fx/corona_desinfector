package com.gendalf.app.annotatoin.config.proxy;

public interface ProxyConfigurator {
    Object replaceWithProxy(Object object, Class implClass);
}
