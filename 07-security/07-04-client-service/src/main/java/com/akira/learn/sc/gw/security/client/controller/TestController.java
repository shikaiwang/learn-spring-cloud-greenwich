package com.akira.learn.sc.gw.security.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        return "client access success";
    }

    @GetMapping("/testProvider")
    public String testProvider() {
        String result = restTemplate.getForObject("http://provider-service/provider/test", String.class);
        return result;
    }


}
