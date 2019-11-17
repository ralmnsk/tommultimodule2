package com.github.ralmnsk.model.role;

import org.springframework.security.core.GrantedAuthority;


public enum ClientType implements GrantedAuthority {
    GUEST,USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
