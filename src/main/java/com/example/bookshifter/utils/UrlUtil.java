package com.example.bookshifter.utils;

import jakarta.servlet.http.HttpServletRequest;

public class UrlUtil {


    private  UrlUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }
    public static String getApplicationUrl(HttpServletRequest request){
        String appURL = request.getRequestURL().toString();
        return  appURL.replace(request.getServletPath(), "");
    }
}
