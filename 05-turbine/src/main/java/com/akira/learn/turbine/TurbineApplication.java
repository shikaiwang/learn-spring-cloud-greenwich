package com.akira.learn.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 等于 @SpringBootApplication @EnableDiscoveryClient @EnableCircuitBreaker
 */
@SpringCloudApplication
@EnableHystrix
@EnableHystrixDashboard
/**
 * 开启断路器监控聚合 这个注解包含了 @EnableDiscoveryClient
 */
@EnableTurbine
public class TurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class, args);
    }
}
