package com.akira.learn.sc.gw.security.core.config;

import com.akira.learn.sc.gw.security.core.interceptor.RestTemplateUserContextInterceptor;
import com.akira.learn.sc.gw.security.core.interceptor.UserContextInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CommonConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserContextInterceptor());
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        //RestTemplate 拦截器，在发送请求前设置鉴权的用户上下文信息
        restTemplate.getInterceptors().add(new RestTemplateUserContextInterceptor());
        return restTemplate;
    }


}
