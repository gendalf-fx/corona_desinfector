package com.gendalf.app;

public class CoronaDesinfector {
    private Announcer announcer = new ConsoleAnnouncer();

    private Policeman policeman = new PolicemanImpl();

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
