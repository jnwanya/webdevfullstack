package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domains.backend.User;

/**
 * Created by Jnwanya on
 * Thu, 28 Dec, 2017.
 */
public class UserUtils {

    private UserUtils(){
       throw new AssertionError("Non instantiable");
    }

    public static User createBasicUser(String username, String email){
        User user = new User();
        user.setPassword("secret");
        user.setFirstName("John");
        user.setLastName("Okafor");
        user.setCountry("NG");
        user.setUsername(username);
        user.setEmail(email);
        user.setEnabled(true);
        user.setPhoneNumber("08067507380");
        user.setDescription("A basic User");
        user.setProfileImageUrl("https://blabla.images.com/basic_user");

        return user;
    }
}
