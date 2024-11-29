package com.example.trip_app.test_data.utils;

import java.util.Random;

public class StringSequenceGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random randomInt = new Random();

    public static String generateRandomString(int length){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < length ; i++){
            result.append(CHARACTERS.charAt(randomInt.nextInt(CHARACTERS.length())));
        }

        return result.toString();
    }
}
