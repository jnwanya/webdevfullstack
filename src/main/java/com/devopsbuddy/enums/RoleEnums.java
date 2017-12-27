package com.devopsbuddy.enums;

/**
 * Created by Jnwanya on
 * Wed, 27 Dec, 2017.
 */
public enum RoleEnums {
    BASIC(1, "ROLE_BASIC"),
    PRO(2, "ROLE_PRO"),
    ADMIN(3, "ROLE_ADMIN");

    private final int id;
    private final String roleName;

    RoleEnums(int id, String roleName){
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
}
