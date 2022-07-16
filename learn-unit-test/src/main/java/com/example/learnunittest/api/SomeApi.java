package com.example.learnunittest.api;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SomeApi {
    private int rate;

    public SomeApi() {
        connect("some-url-to-update-rate");
    }

    public int getRate() {
        return rate;
    }

    public void connect(String url) {
        // Originally
        // Using External API
        //
        // Use Random Instead.

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        rate = random.nextInt(300) + 1000;
    }
}
