package com.gendalf.app.annotatoin.config.initialiser.impl;

import java.lang.reflect.Field;

import com.gendalf.app.annotatoin.InjectByType;
import com.gendalf.app.annotatoin.config.initialiser.ObjectConfigurator;
import com.gendalf.app.context.ApplicationContext;
import lombok.SneakyThrows;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {
    @SneakyThrows
    @Override
    public void configure(Object object, ApplicationContext context) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                field.setAccessible(true);
                Object instance = context.getObject(field.getType());
                field.set(object, instance);
            }
        }
    }
}
