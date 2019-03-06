package com.akira.learn.sc.gw.security.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @GetMapping("/test")
    public String test() {

        return "success access provider service";
    }
}
