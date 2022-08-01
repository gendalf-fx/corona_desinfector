package com.gendalf.app.servcie.recomender.impl;

import com.gendalf.app.annotatoin.InjectProperty;
import com.gendalf.app.annotatoin.Singleton;
import com.gendalf.app.servcie.recomender.Recommender;

@Singleton
@Deprecated
public class RecommenderImpl implements Recommender {
    @InjectProperty
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println("In order to protect from Corona - drink " + alcohol);
    }
}
