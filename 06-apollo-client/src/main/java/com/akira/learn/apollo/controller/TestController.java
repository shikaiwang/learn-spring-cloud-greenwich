package com.akira.learn.apollo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configConsumer")
@RefreshScope
public class TestController {
    @Value("${config_info}")
    private String config;

    @RequestMapping("/getConfigInfo")
    public String getConfig() {
        return config;
    }
}
