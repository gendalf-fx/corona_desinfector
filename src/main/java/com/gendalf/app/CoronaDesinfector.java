package com.gendalf.app;

import com.gendalf.app.config.ObjectFactory;
import com.gendalf.app.data.Room;
import com.gendalf.app.servcie.announcer.Announcer;
import com.gendalf.app.servcie.policeman.Policeman;

public class CoronaDesinfector {
    private Announcer announcer = ObjectFactory.getInstance().createObject(Announcer.class);

    private Policeman policeman = ObjectFactory.getInstance().createObject(Policeman.class);

    public void start(Room room) {
        announcer.announce("The disinfection is starting, everyone - go away!");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("Try to come back");
    }

    private void desinfect(Room room) {
        System.out.println("Prayer is read: 'Corona go away!' - Virus has gone");
    }
}
