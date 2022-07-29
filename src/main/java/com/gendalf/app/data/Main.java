package com.gendalf.app.data;

import com.gendalf.app.CoronaDesinfector;

public class Main {
    public static void main(String[] args) {
        CoronaDesinfector coronaDesinfector = new CoronaDesinfector();
        coronaDesinfector.start(new Room());
    }
}
