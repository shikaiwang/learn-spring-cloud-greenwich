package com.akira.learn.sc.gw.feign.service;

import com.akira.learn.sc.gw.feign.service.fallback.HiServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * fallback 直接配置 fallback 执行类必须实现接口，所以无法获取到异常具体信息
 * fallback = HiServiceFallback.class)
 * <p>
 * fallback 工厂配置 需要实现 FallbackFactory接口
 * fallbackFactory = HiServiceFallbackFactory.class
 * <p>
 * 当使用fallback后 容器内必定有多个接口实现，所以@FeignClient有@Primary的效果，有需要可以用primary=false关闭，
 * <p>
 * contextId g版新属性
 *
 * @see TestService 的name属性
 * g版不允许多个feignClient name相同，所以可以用contextID来区分，
 * 要不就用SpringBoot 2.1.x的新属性 spring.main.allow-bean-definition-overriding=true，但是配置到配置中心的话，无效
 * 要不就所有来自一个服务的接口全部由一个骚东西来继承
 */
@FeignClient(value = "service-hello-eureka", contextId = "hiService", fallbackFactory = HiServiceFallbackFactory.class)
public interface HiService {

    @GetMapping("/hi")
    String sayHiFromHiService(@RequestParam("name") String name);
}
