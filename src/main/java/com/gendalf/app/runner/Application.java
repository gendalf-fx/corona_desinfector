package com.gendalf.app.runner;

import java.util.Map;

import com.gendalf.app.config.Config;
import com.gendalf.app.config.ObjectFactory;
import com.gendalf.app.config.impl.JavaConfig;
import com.gendalf.app.context.ApplicationContext;

public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> inf2ImplClass) {
        Config config = new JavaConfig(packageToScan, inf2ImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        //TODO: init all not lazy Singletons
        context.setFactory(objectFactory);
        return context;
    }
}
