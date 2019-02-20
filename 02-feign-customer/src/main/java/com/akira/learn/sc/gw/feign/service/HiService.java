package com.akira.learn.sc.gw.feign.service;

import com.akira.learn.sc.gw.feign.service.fallback.HiServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hello-eureka",fallback = HiServiceFallback.class)
public interface HiService {

    @GetMapping("/hi")
    String sayHiFromHiService(@RequestParam("name") String name);
}
