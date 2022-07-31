package com.gendalf.app.servcie.announcer.impl;

import javax.annotation.PostConstruct;

import com.gendalf.app.annotatoin.InjectByType;
import com.gendalf.app.config.ObjectFactory;
import com.gendalf.app.servcie.announcer.Announcer;
import com.gendalf.app.servcie.recomender.Recommender;

public class ConsoleAnnouncer implements Announcer {
    @InjectByType
    private Recommender recommender;

    @PostConstruct
    public void init() {
        System.out.println(recommender.getClass());
    }

    @Override
    public void announce(String message) {
        System.out.println(message);
        recommender.recommend();
    }
}
