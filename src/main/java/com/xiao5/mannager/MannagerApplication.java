package com.xiao5.mannager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "com.xiao5.mannager")
public class MannagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MannagerApplication.class, args);
    }

}
