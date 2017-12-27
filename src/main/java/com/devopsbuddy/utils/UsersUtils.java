package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domains.backend.User;

/**
 * Created by Jnwanya on
 * Thu, 28 Dec, 2017.
 */
public class UsersUtils {

    private UsersUtils(){
       throw new AssertionError("Non instantiable");
    }

    public static User createBasicUser(){
        User user = new User();
        user.setFirstName("Justin");
        user.setLastName("Nwanya");
        user.setCountry("NG");
        user.setUsername("jnwanya");
        user.setEnabled(true);
        user.setPhoneNumber("08067507380");
        user.setDescription("A basic User");
        user.setProfileImageUrl("https://blabla.images.com/basic_user");

        return user;
    }
}
