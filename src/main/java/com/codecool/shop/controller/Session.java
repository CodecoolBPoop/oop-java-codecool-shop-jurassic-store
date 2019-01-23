package com.codecool.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Session {
    private static HttpSession session= null;

    private Session(HttpServletRequest request) {
        session = request.getSession();
    }

    public static HttpSession getInstance(HttpServletRequest request) {
        if (session == null) {
            new Session(request);
                    }
        return session;
    }

}
