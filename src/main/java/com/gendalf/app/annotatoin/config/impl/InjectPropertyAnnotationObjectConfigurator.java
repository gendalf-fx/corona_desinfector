package com.gendalf.app.annotatoin.config.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.gendalf.app.annotatoin.InjectProperty;
import com.gendalf.app.annotatoin.config.ObjectConfigurator;
import com.gendalf.app.context.ApplicationContext;
import lombok.SneakyThrows;

public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    private final Map<String, String> propertiesMap;

    @SneakyThrows
    public InjectPropertyAnnotationObjectConfigurator() {
        String path =
            Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("application.properties")).getPath();
        try (Stream<String> lines = new BufferedReader(new FileReader(path)).lines()) {
            propertiesMap =
                lines.map(line -> line.split("=")).collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
        }
    }

    @SneakyThrows
    @Override
    public void configure(Object object, ApplicationContext context) {
        Class<?> implClass = object.getClass();

        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty property = field.getAnnotation(InjectProperty.class);
            if (property != null) {
                String value = property.value().isEmpty() ? propertiesMap.get(field.getName()) :
                    propertiesMap.get(property.value());
                field.setAccessible(true);
                field.set(object, value);
            }
        }
    }
}
