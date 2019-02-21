package com.akira.learn.getway;

import com.akira.learn.getway.filterFactory.CustomGatewayFilterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }

//    2.代码实现路由
//    @Bean
//    public RouteLocator demoRoutes(RouteLocatorBuilder builder) {
//        final String uri = "http://httpbin.org";
//        return builder.routes()
//                .route(predicateSpec -> predicateSpec
//                        .path("/get")
//                        .filters(f -> f
//                                .addRequestHeader("addedHeader", "addedHeaderValue"))
//                        .uri(uri))
//                //测试熔断 curl --dump-header - --header 'Host: www.lolo.com' http://localhost:9401
//                .route(predicateSpec -> predicateSpec
//                        .host("*.lolo.com")
//                        .filters(f -> f
//                                .hystrix(config -> config
//                                        .setName("momoda")
//                                        .setFallbackUri("forward:/fallback")))
//                        .uri(uri))
//                .build();
//    }
//
//    @GetMapping("/fallback")
//    public Mono<String> fallback() {
//        return Mono.just("喔嚯");
//    }

//    @Bean
//    /**
//     * 3.自定义过滤器
//     */
//    public RouteLocator customFilter(RouteLocatorBuilder builder) {
//        final String uri = "http://httpbin.org";
//        return builder.routes()
//                .route(predicateSpec -> predicateSpec
//                        .path("/get")
//                        .filters(f -> f.filter(new CustomFilter()))
//                        .uri(uri)
//                        .order(0)
//                        .id("custom_filter"))
//                .build();
//    }

    /**
     * 4.自定义过滤器工厂
     */
    @Bean
    public CustomGatewayFilterFactory customGatewayFilterFactory() {
        return new CustomGatewayFilterFactory();
    }

    /**
     * 5.全局过滤器
     */
//    @Bean
//    public TokenFilter tokenFilter() {
//        return new TokenFilter();
//    }


    /**
     * 6. 限流 根据host产生key
     */
    @Bean
    @Primary
    public KeyResolver hostAddrKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }

    /**
     * 6. 限流 根据 uri 产生key
     */
    @Bean
    public KeyResolver uriKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }

    /**
     * 6. 限流 根据纬度产生key
     */
    @Bean
    public KeyResolver bizKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("biz"));
    }




}
