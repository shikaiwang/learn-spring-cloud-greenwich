package com.akira.learn.sc.gw.feign.service.fallback;

import com.akira.learn.feign.vo.RemoteAPIParam;
import com.akira.learn.feign.vo.RemoteAPIResult;
import com.akira.learn.sc.gw.feign.service.HiService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 熔断处理
 */
@Component
public class HiServiceFallbackFactory implements FallbackFactory<HiService> {

    @Override
    public HiService create(Throwable throwable) {

        return new HiService() {
            public RemoteAPIResult testPostAPI(RemoteAPIParam param) {
                return null;
            }

            public RemoteAPIResult testGetAPI(String id) {
                return null;
            }

            @Override
            public String sayHiFromHiService(String name) {
                return name+ "here is fallback error:" + throwable.getMessage();
            }
        };
    }
}
