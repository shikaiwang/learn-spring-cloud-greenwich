package com.akira.learn.sc.gw.feign.service.fallback;

import com.akira.learn.feign.vo.RemoteAPIParam;
import com.akira.learn.feign.vo.RemoteAPIResult;
import com.akira.learn.sc.gw.feign.service.HiService;
import org.springframework.stereotype.Component;

/**
 * 熔断处理
 */
@Component
public class HiServiceFallback implements HiService {
    @Override
    public String sayHiFromHiService(String name) {
        return "failed this is in fallback";
    }

    public RemoteAPIResult testPostAPI(RemoteAPIParam param) {
        return null;
    }

    public RemoteAPIResult testGetAPI(String id) {
        return null;
    }
}
