package com.akira.learn.sc.gw.security.client.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.*;

@FeignClient(name = "provider-service",configuration = ProviderService.ProviderServiceConfiguration.class)
public interface ProviderService {

    @GetMapping("/provider/test")
    Spring providerTest();


    @Configuration
    class ProviderServiceConfiguration {



    }
}
