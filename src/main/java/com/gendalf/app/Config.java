package com.gendalf.app;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> inf);
}
