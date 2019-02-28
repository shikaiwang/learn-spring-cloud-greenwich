package com.akira.learn.sc.gw.eureka.controller;

import com.akira.learn.feign.api.RemoteAPI2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController2 implements RemoteAPI2 {
    @Override
    public Date dateTest(Date date) {
        return date;
    }

    @GetMapping("/notIn")
    public String notInDoc() {
        return "notInDoc";
    }
}
