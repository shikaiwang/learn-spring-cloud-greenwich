package com.akira.learn.sc.gw.feign.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;

@Configuration
public class FeignFormatterRegisterConfiguration implements FeignFormatterRegistrar {

    @Autowired
    private FeignDateFormatter dateFormatter;

    @Override
    public void registerFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addFormatter(dateFormatter);
    }


}
