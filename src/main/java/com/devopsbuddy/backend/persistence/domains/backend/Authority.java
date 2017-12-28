package com.devopsbuddy.backend.persistence.domains.backend;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Jnwanya on
 * Thu, 28 Dec, 2017.
 */
public class Authority implements GrantedAuthority {

    private final String authority;

    public Authority(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
