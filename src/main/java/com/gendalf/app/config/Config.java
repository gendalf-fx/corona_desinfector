package com.gendalf.app.config;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> inf);
}
