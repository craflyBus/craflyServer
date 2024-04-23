package com.crafly.craflyserver;

import org.springframework.boot.runApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class CraflyServerApplication
fun main(args: Array<String>) {
    runApplication<CraflyServerApplication>(*args)
}
