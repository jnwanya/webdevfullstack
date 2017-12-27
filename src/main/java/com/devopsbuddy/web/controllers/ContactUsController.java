package com.devopsbuddy.web.controllers;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jnwanya on
 * Tue, 26 Dec, 2017.
 */

@Controller
public class ContactUsController {

    private static final Logger LOG = LoggerFactory.getLogger(ContactUsController.class);

    /* the key which identifies the feedback payload in the Model */
    public static final String FEEDBACK_MODEL_KEY = "feedback";

    public static final String CONTACT_US_VIEW_NAME = "contact/contact";

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactGet(ModelMap modelMap){
        FeedbackPojo feedbackPojo = new FeedbackPojo();
        modelMap.addAttribute(FEEDBACK_MODEL_KEY, feedbackPojo);
        return CONTACT_US_VIEW_NAME;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String contactPost(@ModelAttribute(FEEDBACK_MODEL_KEY) FeedbackPojo feedbackPojo){
        LOG.debug("Feedback pojo content: {}", feedbackPojo);
        this.emailService.sendFeedbackEmail(feedbackPojo);
        return CONTACT_US_VIEW_NAME;
    }
}
