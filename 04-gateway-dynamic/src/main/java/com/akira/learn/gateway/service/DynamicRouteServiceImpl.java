package com.akira.learn.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher eventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    public boolean add(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.eventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return true;
    }

    public boolean update(RouteDefinition definition) {
        try {
            routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            System.out.println(e);
        }
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();

        this.eventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return true;
    }

    public boolean delete(String id) {
        routeDefinitionWriter.delete(Mono.just(id)).subscribe();
        return true;
    }
}
