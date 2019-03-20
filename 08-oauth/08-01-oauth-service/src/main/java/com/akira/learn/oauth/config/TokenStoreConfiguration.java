package com.akira.learn.oauth.config;

import com.akira.learn.oauth.util.RandmoAuthenticationKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class TokenStoreConfiguration {


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    /**
     * 默认使用redis token store
     */
    @Bean
    @ConditionalOnProperty(prefix = "access.token.store", name = "type", havingValue = "redis",matchIfMissing = true)
    public TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        //解决同一username每次登陆access_token都相同的问题
//        redisTokenStore.setAuthenticationKeyGenerator(new RandmoAuthenticationKeyGenerator());
        return redisTokenStore;
    }

    /**
     * 根据配置文件开启jwt token store
     */
    @Bean
    @ConditionalOnProperty(prefix = "access.token.store", name = "type", havingValue = "jwt")
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }


    @Bean
    @ConditionalOnProperty(prefix = "access.token.store", name = "type", havingValue = "jwt")
    public JwtAccessTokenConverter accessTokenConverter() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt-oauth.jks"), "wxoauth123".toCharArray());
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                OAuth2AccessToken token = super.enhance(accessToken, authentication);

                return token;
            }
        };
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt-oauth"));


        return jwtAccessTokenConverter;
    }
}
