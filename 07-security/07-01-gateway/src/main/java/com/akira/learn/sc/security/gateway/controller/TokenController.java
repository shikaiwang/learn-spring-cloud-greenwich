package com.akira.learn.sc.security.gateway.controller;

import com.akira.learn.sc.security.gateway.util.JWTUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @GetMapping("token/{name}")
    public String token(@PathVariable("name") String name) {
        return JWTUtil.generateToken(name);
    }
}
