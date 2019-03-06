package com.akira.learn.sc.gw.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

//@Component
public class TokenInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return;
        }
        //往下传递
        template.header("oauthToken", getHeaders(request).get("oauthToken"));
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ServletRequestAttributes.class.cast(RequestContextHolder.currentRequestAttributes()).getRequest();

        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
