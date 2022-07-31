package com.gendalf.app.servcie.announcer;

import com.gendalf.app.config.ObjectFactory;

public class ConsoleAnnouncer implements Announcer {

    private Recommender recommender = ObjectFactory.getInstance().createObject(Recommender.class);

    @Override
    public void announce(String message) {
        System.out.println(message);
        recommender.recommend();
    }
}
