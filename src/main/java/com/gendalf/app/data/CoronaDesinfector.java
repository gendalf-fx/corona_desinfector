package com.gendalf.app.data;

import com.gendalf.app.annotatoin.InjectByType;
import com.gendalf.app.servcie.announcer.Announcer;
import com.gendalf.app.servcie.policeman.Policeman;

@Deprecated
public class CoronaDesinfector {

    @InjectByType
    private Announcer announcer;

    @InjectByType
    private Policeman policeman;

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
