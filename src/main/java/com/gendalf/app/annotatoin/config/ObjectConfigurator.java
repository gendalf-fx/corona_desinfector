package com.gendalf.app.annotatoin.config;

import com.gendalf.app.context.ApplicationContext;

public interface ObjectConfigurator {
    void configure(Object object, ApplicationContext context);
}
