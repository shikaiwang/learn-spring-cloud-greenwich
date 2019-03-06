package com.akira.learn.sc.gw.security.core.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.akira.learn.sc.gw.security.core.util.UserPermissionUtil;
import com.akira.learn.sc.gw.security.core.vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserContextInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(UserContextInterceptor.class);

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = this.getUser(request);
        UserPermissionUtil.permission(user);
        if (!UserPermissionUtil.verify(user, request)) {
            response.setHeader("Content-Type", "application/json");
            String jsonStr = mapper.writeValueAsString("no permission access service, please check");
            response.getWriter().write(jsonStr);
            response.getWriter().flush();
            response.getWriter().close();
            throw new PermissionException("no permission access service, please check");

        }
        UserContextHolder.set(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHolder.shutdown();
    }

    private User getUser(HttpServletRequest request) {
        String userId = request.getHeader("x-user-id");
        String userName = request.getHeader("x-user-name");
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        return user;
    }

    static class PermissionException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        public PermissionException(String msg) {
            super(msg);
        }
    }
}
