package com.akira.learn.sc.gw.security.core.util;

import org.springframework.util.StringUtils;
import com.akira.learn.sc.gw.security.core.vo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UserPermissionUtil {

    public static boolean verify(User user, HttpServletRequest request) {
        String serviceName = request.getHeader("x-user-serviceName");
        if (StringUtils.isEmpty(user)) {
            return false;
        }
        List<String> permissions = user.getAllowPermissionService();
        for (String permission : permissions) {
            if (serviceName.equalsIgnoreCase(permission)) {
                return true;
            }
        }
        return false;
    }

    public static void permission(User user) {
        List<String> allowPermissionService = new ArrayList<String>();
        if(user.getUserName().equals("admin")) {
            allowPermissionService.add("client-service");
            allowPermissionService.add("provider-service");
            user.setAllowPermissionService(allowPermissionService);
        }else if(user.getUserName().equals("spring")) {
            allowPermissionService.add("client-service");
            user.setAllowPermissionService(allowPermissionService);
        }
        user.setAllowPermissionService(allowPermissionService);
    }
}
