package com.gendalf.app.servcie.announcer;

public class RecommenderImpl implements Recommender {
    @InjectProperty("HUINIA")
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println("In order to protect from Corona - drink " + alcohol);
    }
}
