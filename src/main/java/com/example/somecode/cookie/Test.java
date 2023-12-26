package com.example.somecode.cookie;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class Test {

    public void setCookie(HttpServletRequest request, HttpServletResponse response) {
        String cookieName = "session-id-cookie";
        String cookieValue = "123";
        Cookie SessionIDCookie = new Cookie(cookieName, cookieValue);
        response.addCookie(SessionIDCookie);
    }

    public void getCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("session-id-cookie")) {
                    log.info("Cookie value" + cookie.getValue());
                }
                log.info("Cookie name:{}, Cookie value:{}", cookie.getName(), cookie.getValue());
            }
        }
    }
}
