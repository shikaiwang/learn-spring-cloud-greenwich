package com.akira.learn.oauth.util;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;

import java.util.UUID;

public class RandmoAuthenticationKeyGenerator implements AuthenticationKeyGenerator {
    @Override
    public String extractKey(OAuth2Authentication authentication) {
        return UUID.randomUUID().toString();
    }
}
