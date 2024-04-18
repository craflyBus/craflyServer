package com.crafly.craflyserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class CraflyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CraflyServerApplication.class, args);
    }

}
