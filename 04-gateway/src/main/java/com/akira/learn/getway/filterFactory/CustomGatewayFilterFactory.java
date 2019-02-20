package com.akira.learn.getway.filterFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class CustomGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomGatewayFilterFactory.Config> {
    private static final Log log = LogFactory.getLog(GatewayFilter.class);

    /**
     * 不调用父类的构造方法会出现转型错误
     */
    public CustomGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange,chain) ->{
            return chain.filter(exchange).then(Mono.fromCallable(() -> {
                if (config.needLog) {
                    log.info("in custom");
                }
                return null;
            }));
        });
    }

    @Override
    /**
     * config 参数列表
     */
    public List<String> shortcutFieldOrder() {

        return Arrays.asList("needLog");
    }

    public static class Config{
        private boolean needLog;

        public boolean isNeedLog() {
            return needLog;
        }

        public void setNeedLog(boolean needLog) {
            this.needLog = needLog;
        }
    }

}
