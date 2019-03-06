package com.akira.learn.sc.gw.feign.service;

import com.akira.learn.feign.api.RemoteAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-hello-eureka",contextId = "testService")
public interface TestService extends RemoteAPI {

//    @Configuration
//    class TestServiceConfiguration {
////
////        @Autowired
////        private TokenInterceptor tokenInterceptor;
//
//        @Bean
//        public Contract feignContract() {
//            return new feign.Contract.Default();
//        }
//        @Bean
//        public List<RequestInterceptor> requestInterceptors() {
//            List<RequestInterceptor> arrayList = new ArrayList<>();
//            arrayList.add(new TokenInterceptor());
//            return arrayList;
//        }
//    }
}
