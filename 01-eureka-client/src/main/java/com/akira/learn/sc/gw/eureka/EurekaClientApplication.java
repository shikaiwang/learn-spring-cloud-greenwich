package com.akira.learn.sc.gw.eureka;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
/**
 * 允许发现服务
 */
@EnableDiscoveryClient
/**
 * 使用Hystrix
 */
@EnableHystrix
/**
 * 开启断路器监控,需要开启对应的端点支持 见properties
 * 仪表页面/hystrix
 * ping /actuator/hystrix.stream
 * 需要先有消费者调用 否则/hystrix 一直loading。actuator/hystrix.stream一直ping不到数据
 */
@EnableHystrixDashboard
/**
 * 使用断路器 支持使用 @HystrixCommand 定义断路器逻辑
 */
@EnableCircuitBreaker
/**
 * @SpringCloudApplication 等于 @SpringBootApplication @EnableDiscoveryClient @EnableCircuitBreaker
 */
//@SpringCloudApplication
public class EurekaClientApplication {

    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }


    @GetMapping("/hi")
    public String home(@RequestParam("name") String name) {
        String result = "hi " + name + " , this is from port:" + port;
        int i = 1 / 0;//By zero 测试熔断
        return result;
    }

    @GetMapping("/hystrixTest")
    @HystrixCommand(fallbackMethod = "testFallback")
    public String testHystrixFallback(@RequestParam("name") String name) {
        int i = 1 / 0;//By zero 测试熔断
        return "ok, " + name;
    }

    public String testFallback(String name) {
        return name + ", emmm,我在熔断逻辑里面咯哟～～";
    }

}
