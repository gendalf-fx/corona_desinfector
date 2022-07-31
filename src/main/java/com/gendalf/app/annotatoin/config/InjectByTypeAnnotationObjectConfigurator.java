package com.gendalf.app.annotatoin.config;

import java.lang.reflect.Field;

import com.gendalf.app.annotatoin.InjectByType;
import com.gendalf.app.config.ObjectFactory;
import lombok.SneakyThrows;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {
    @SneakyThrows
    @Override
    public void configure(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                field.setAccessible(true);
                Object instance = ObjectFactory.getInstance().createObject(field.getType());
                field.set(object, instance);
            }
        }
    }
}
