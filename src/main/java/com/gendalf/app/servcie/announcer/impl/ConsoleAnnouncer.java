package com.gendalf.app.servcie.announcer.impl;

import com.gendalf.app.config.ObjectFactory;
import com.gendalf.app.servcie.announcer.Announcer;
import com.gendalf.app.servcie.recomender.Recommender;

public class ConsoleAnnouncer implements Announcer {

    private Recommender recommender = ObjectFactory.getInstance().createObject(Recommender.class);

    @Override
    public void announce(String message) {
        System.out.println(message);
        recommender.recommend();
    }
}
