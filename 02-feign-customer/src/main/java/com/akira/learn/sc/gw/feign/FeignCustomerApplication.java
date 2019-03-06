package com.akira.learn.sc.gw.feign;

import feign.Logger;
import feign.QueryMapEncoder;
import feign.querymap.BeanQueryMapEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
public class FeignCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignCustomerApplication.class, args);
    }

    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }


//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder(Retryer retryer,QueryMapEncoder beanQueryMapEncoder) {
//        Feign.Builder builder = Feign.builder();
//        builder.queryMapEncoder(beanQueryMapEncoder);
//        return builder.retryer(retryer);
//    }
}
