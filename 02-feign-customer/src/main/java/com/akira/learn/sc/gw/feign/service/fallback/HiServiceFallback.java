package com.akira.learn.sc.gw.feign.service.fallback;

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
}
