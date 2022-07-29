package com.gendalf.app;

public class ConsoleAnnouncer implements Announcer{
    @Override
    public void announce(String message) {
        System.out.println(message);
    }
}
