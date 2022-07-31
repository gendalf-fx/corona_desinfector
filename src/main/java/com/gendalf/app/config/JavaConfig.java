package com.gendalf.app.config;

import java.util.Map;
import java.util.Set;

import lombok.Getter;
import org.reflections.Reflections;

public class JavaConfig implements Config {
    @Getter
    private final Reflections scanner;

    private final Map<Class, Class> inf2ImplClass;

    public JavaConfig(String packageToScan, Map<Class, Class> inf2ImplClass) {
        this.scanner = new Reflections(packageToScan);
        this.inf2ImplClass = inf2ImplClass;
    }


    @Override
    public <T> Class getImplClass(Class<T> inf) {
        return inf2ImplClass.computeIfAbsent(inf, aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(inf);
            if (classes.size() != 1) {
                throw new RuntimeException(inf.getSimpleName() + " has 0 or more than 1 implementations, please " +
                    "update your config!");
            }
            return classes.iterator().next();
        });
    }
}
