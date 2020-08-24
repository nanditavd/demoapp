package com.nd.trade.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.nd.trade.repository")
@EntityScan("com.nd.trade.model")
public class DemoTradeApplication {



    public static void main(String[] args) {
         SpringApplication.run(DemoTradeApplication.class, args);
    }

}