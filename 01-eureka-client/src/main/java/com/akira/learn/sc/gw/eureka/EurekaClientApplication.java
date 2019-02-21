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
//允许发现服务
@EnableDiscoveryClient
//使用Hystrix
@EnableHystrix
//开启断路器监控
//仪表页面/hystrix
// /actuator/hystrix.stream
//需要先有消费者调用 否则/hystrix 一直loading。actuator/hystrix.stream一直ping不到数据
@EnableHystrixDashboard
//使用断路器
@EnableCircuitBreaker
public class EurekaClientApplication {

    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }


    @GetMapping("/hi")
    @HystrixCommand(fallbackMethod = "honeFallback")
    public String home(@RequestParam("name") String name) {
        return "hi " + name + " , this is from port:" + port;
    }

    public String honeFallback(String name) {
        return "emmmm" + name + " i'm error! this is fallback method";
    }

}
