package com.gendalf.app;

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
