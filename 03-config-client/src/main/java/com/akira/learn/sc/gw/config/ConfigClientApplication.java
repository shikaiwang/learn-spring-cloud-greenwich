package com.akira.learn.sc.gw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
//RefreshScope 刷新注解设置到需要刷新到类上，用post方法访问/actuator/bus-refresh 从配置中心刷新远端配置
//curl -X POST localhost:9310/actuator/bus-refresh
//单个服务接收到刷新请求后会通过消息总线（Bus）向其他服务传递从而使整个服务集群都达到更新配置文件
//另外，/actuator/bus-refresh接口可以指定服务，即使用"destination"参数，比如 “/actuator/bus-refresh?destination=customers:**” 即刷新服务名为customers的所有服务。
@RefreshScope
public class ConfigClientApplication {

    @Value("${server.port}")
    private String bootProp;
    @Value("${wohuhu}")
    private String appProp;
    @Value("${demop}")
    private String remoteConfigProp;
    @Value("${trunk}")
    private String trunk;

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @GetMapping("/config/test")
    public String getConfigTest() {
        return "bootProp:" + bootProp + " ,appProp:" + appProp + " ,remoteProp:" + remoteConfigProp + " ,trunk:" + trunk;
    }
}
