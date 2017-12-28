package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domains.backend.User;
import com.devopsbuddy.web.controllers.ForgotMyPasswordController;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * Builds and returns the URL to reset the user password.
     * @param request The Http Servlet Request
     * @param userId The user id
     * @param token The token
     * @return the URL to reset the user password.
     */
    public static String createPasswordResetUrl(HttpServletRequest request, long userId, String token) {
        String passwordResetUrl =
                request.getScheme() +
                        "://" +
                        request.getServerName() +
                        ":" +
                        request.getServerPort() +
                        request.getContextPath() +
                        ForgotMyPasswordController.CHANGE_PASSWORD_PATH +
                        "?id=" +
                        userId +
                        "&token=" +
                        token;

        return passwordResetUrl;
    }

    /*public static <T extends BasicAccountPayload> User fromWebUserToDomainUser(T frontendPayload) {
        User user = new User();
        user.setUsername(frontendPayload.getUsername());
        user.setPassword(frontendPayload.getPassword());
        user.setFirstName(frontendPayload.getFirstName());
        user.setLastName(frontendPayload.getLastName());
        user.setEmail(frontendPayload.getEmail());
        user.setPhoneNumber(frontendPayload.getPhoneNumber());
        user.setCountry(frontendPayload.getCountry());
        user.setEnabled(true);
        user.setDescription(frontendPayload.getDescription());

        return user;
    }*/
}
