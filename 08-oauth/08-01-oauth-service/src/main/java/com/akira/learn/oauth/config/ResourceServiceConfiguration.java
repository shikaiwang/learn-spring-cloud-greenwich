package com.akira.learn.oauth.config;

import com.akira.learn.oauth.constant.DefaultPermitUrl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableResourceServer
public class ResourceServiceConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(new OAuth2RequestedMatcher()).authorizeRequests()
                .antMatchers(DefaultPermitUrl.all())
                .permitAll() // 放开权限的url
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private static class OAuth2RequestedMatcher implements RequestMatcher {
        @Override
        public boolean matches(javax.servlet.http.HttpServletRequest request) {
            // 请求参数中包含access_token参数
            if (request.getParameter(OAuth2AccessToken.ACCESS_TOKEN) != null) {
                return true;
            }

            // 头部的Authorization值以Bearer开头
            String auth = request.getHeader("Authorization");
            if (auth != null) {
                return auth.startsWith(OAuth2AccessToken.BEARER_TYPE);
            }

            return false;
        }
    }

}
