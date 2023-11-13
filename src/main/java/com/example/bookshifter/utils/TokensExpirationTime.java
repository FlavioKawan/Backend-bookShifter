package com.example.bookshifter.utils;

import java.util.Calendar;
import java.util.Date;

public class TokensExpirationTime {

    private TokensExpirationTime(){
        throw new IllegalStateException("Utility class");
    }
    private static final int EXPIRATION_TIME = 5;
    private static final int JWT_EXPIRATION_TIME = 30;
    public static Date getExpirantionTime(){
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }

    public static Date getJWTExpirationTime(){
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, JWT_EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}
