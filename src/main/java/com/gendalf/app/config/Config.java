package com.gendalf.app.config;

import org.reflections.Reflections;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> inf);

    Reflections getScanner();
}
