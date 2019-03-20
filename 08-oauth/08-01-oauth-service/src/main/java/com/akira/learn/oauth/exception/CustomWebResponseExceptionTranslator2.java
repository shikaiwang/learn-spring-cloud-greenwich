package com.akira.learn.oauth.exception;

import com.akira.learn.oauth.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

//@Component
public class CustomWebResponseExceptionTranslator2 implements WebResponseExceptionTranslator<Result<Void>> {
    @Override
    public ResponseEntity<Result<Void>> translate(Exception e) throws Exception {
        Result<Void> result = new Result<>();
        result.setCode(-1);
        if (e instanceof OAuth2Exception) {
            result.setMessage(e.getMessage());
            OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
            return ResponseEntity
                    .status(oAuth2Exception.getHttpErrorCode())
                    .body(result);
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(result);
        }
    }
}
