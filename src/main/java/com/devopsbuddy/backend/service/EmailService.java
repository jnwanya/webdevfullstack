package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.mail.SimpleMailMessage;

/**
 * Contract for email service
 * Created by Jnwanya on
 * Wed, 27 Dec, 2017.
 */
public interface EmailService {
    /**
     * Sends an email with the content in the Feedback pojo
     * @param feedbackPojo the feedbackpojo
     */
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo);

    /**
     * sends an email with the content of SimpleMailMessage
     * @param message an object containing the mail content
     */
    public void sendGenericEmailMessage(SimpleMailMessage message);
}
