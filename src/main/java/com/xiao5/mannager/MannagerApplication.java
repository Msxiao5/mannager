package com.xiao5.mannager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MannagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MannagerApplication.class, args);
    }

}
