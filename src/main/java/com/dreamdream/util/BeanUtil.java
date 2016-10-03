package com.dreamdream.util;

import org.springframework.context.ApplicationContext;

public class BeanUtil {

    private static ApplicationContext context;
    
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static void setContext(ApplicationContext context) {
        BeanUtil.context = context;
    }
}
