package com.akira.learn.sc.gw.security.core.interceptor;

import com.akira.learn.sc.gw.security.core.vo.User;

public class UserContextHolder {
    private static ThreadLocal<User> context = new ThreadLocal<>();

    public static User currentUser() {
        return context.get();
    }

    public static void set(User user) {
        context.set(user);
    }

    public static void shutdown() {
        context.remove();
    }

}
