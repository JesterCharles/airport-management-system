package com.revature.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class AirportManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportManagementSystemApplication.class, args);
    }

}
