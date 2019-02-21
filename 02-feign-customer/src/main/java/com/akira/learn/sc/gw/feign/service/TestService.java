package com.akira.learn.sc.gw.feign.service;

import com.akira.learn.feign.api.RemoteAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-hello-eureka",contextId = "testService")
public interface TestService extends RemoteAPI {
}
