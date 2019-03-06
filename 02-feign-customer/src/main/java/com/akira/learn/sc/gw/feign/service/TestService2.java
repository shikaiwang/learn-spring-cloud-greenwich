package com.akira.learn.sc.gw.feign.service;

import com.akira.learn.feign.api.RemoteAPI2;
import feign.QueryMapEncoder;
import feign.querymap.BeanQueryMapEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@FeignClient(value = "service-hello-eureka",contextId = "testService2")
public interface TestService2 extends RemoteAPI2 {



}
