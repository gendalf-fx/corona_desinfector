package com.gendalf.app;

import java.util.Set;

import org.reflections.Reflections;

public class JavaConfig implements Config {

    private Reflections scanner;

    public JavaConfig(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }


    @Override
    public <T> Class<? extends T> getImplClass(Class<T> inf) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(inf);
        if (classes.size() != 1) {
            throw new RuntimeException(inf.getSimpleName() + " has 0 or more than 1 implementations!");
        }


        return classes.iterator().next();
    }
}
