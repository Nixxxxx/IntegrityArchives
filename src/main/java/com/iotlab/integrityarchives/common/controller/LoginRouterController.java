package com.iotlab.integrityarchives.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginRouterController {

    @GetMapping(value = {"/login"})
    public String login() {
        return "login/login";
    }

}
