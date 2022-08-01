package com.gendalf.app.annotatoin.config.initialiser;

import com.gendalf.app.context.ApplicationContext;

public interface ObjectConfigurator {
    void configure(Object object, ApplicationContext context);
}
