package org.example.notifycatsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotifycatsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifycatsysApplication.class, args);
    }

}
