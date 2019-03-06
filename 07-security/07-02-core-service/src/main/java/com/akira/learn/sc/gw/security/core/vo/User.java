package com.akira.learn.sc.gw.security.core.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 293372717088085477L;

    public final static String CONTEXT_KEY_USER_ID = "x-user-id";

    private String userId;

    private String userName;

    private List<String> allowPermissionService = new ArrayList<>();
}
