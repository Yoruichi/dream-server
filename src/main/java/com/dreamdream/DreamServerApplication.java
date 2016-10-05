package com.dreamdream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.dreamdream.util.BeanUtil;

@SpringBootApplication
public class DreamServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext appContext = SpringApplication.run(DreamServerApplication.class, args);
        BeanUtil.setContext(appContext);
    }
}
