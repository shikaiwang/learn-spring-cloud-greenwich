package com.akira.learn.sc.security.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SecurityGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityGatewayApplication.class, args);
    }

}
