package com.akira.learn.sc.gw.feign.controller;

import com.akira.learn.sc.gw.feign.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    private HiService hiService;

    @GetMapping("/hi")
    public String sayHi(@RequestParam("name") String name) {
        return hiService.sayHiFromHiService(name);
    }
}
