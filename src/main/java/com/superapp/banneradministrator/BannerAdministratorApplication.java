package com.superapp.banneradministrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BannerAdministratorApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(BannerAdministratorApplication.class, args);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
