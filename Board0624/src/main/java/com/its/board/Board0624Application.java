package com.its.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Board0624Application {

    public static void main(String[] args) {
        SpringApplication.run(Board0624Application.class, args);
    }

}
