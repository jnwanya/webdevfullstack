package com.devopsjustin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jnwanya on
 * Mon, 25 Dec, 2017.
 */
@Controller
public class HelloworldController {

    @RequestMapping("/")
    public String sayHello(){
        return "index";
    }
}
