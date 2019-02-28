package com.akira.learn.gateway.config;

import com.akira.learn.gateway.service.DynamicRouteServiceImpl;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ConfigRefresh implements ApplicationContextAware {

    public static final String SPRING_CLOUD_GATEWAY_PREFIX = "spring.cloud.gateway.";

    private final GatewayProperties gatewayProperties;

    private final DynamicRouteServiceImpl dynamicRouteService;

    private ApplicationContext applicationContext;

    public ConfigRefresh(GatewayProperties gatewayProperties, DynamicRouteServiceImpl dynamicRouteService) {
        this.gatewayProperties = gatewayProperties;
        this.dynamicRouteService = dynamicRouteService;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

    @ApolloConfigChangeListener("TEST1.gateway-config")
    public void onChange(ConfigChangeEvent changeEvent) {
        boolean gatewayPropertiesChanged = false;
        for (String key : changeEvent.changedKeys()) {
            if (key.startsWith(SPRING_CLOUD_GATEWAY_PREFIX)) {
                gatewayPropertiesChanged = true;
                break;
            }
        }

        if (gatewayPropertiesChanged) {
            this.refreshGatewayProperties(changeEvent);
        }
    }

    private void refreshGatewayProperties(ConfigChangeEvent changeEvent) {
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
        gatewayProperties.getRoutes().forEach(dynamicRouteService::update);
    }
}
