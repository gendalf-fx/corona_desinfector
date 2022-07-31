package com.gendalf.app.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.gendalf.app.servcie.announcer.InjectProperty;
import com.gendalf.app.servcie.policeman.AngerPoliceman;
import com.gendalf.app.servcie.policeman.Policeman;
import lombok.SneakyThrows;

public class ObjectFactory {
    private static ObjectFactory instance = new ObjectFactory();

    private Config config;

    public ObjectFactory() {
        this.config = new JavaConfig("com.gendalf.app", new HashMap<>(Map.of(Policeman.class, AngerPoliceman.class)));
    }

    public static ObjectFactory getInstance() {
        return instance;
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        T t = implClass.getDeclaredConstructor().newInstance();
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty property = field.getAnnotation(InjectProperty.class);
            String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
            Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
            Map<String, String> propertiesMap =
                lines.map(line -> line.split("=")).collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
            if (property != null) {
                String value = property.value().isEmpty() ? propertiesMap.get(field.getName()) : property.value();
                field.setAccessible(true);
                field.set(t, value);
            }
        }

        return t;
    }
}
