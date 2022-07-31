package com.gendalf.app.servcie.policeman.impl;

import com.gendalf.app.servcie.policeman.Policeman;

public class AngerPoliceman implements Policeman {
    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("I will kill everyone! Go away!");
    }
}
