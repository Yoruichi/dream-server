package com.dreamdream.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.google.common.base.Strings;

public class BeanUtil {

    private static ApplicationContext context;
    
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static void setContext(ApplicationContext context) {
        BeanUtil.context = context;
    }
    
    public static String getSessionIdFromHeader(HttpServletRequest req) {
        String sessionId = null;
        sessionId = req.getHeader("SESSION");
        if (Strings.isNullOrEmpty(sessionId)) {
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName()))
                    sessionId = cookie.getValue();
            }
        }
        return sessionId;
    }
}
