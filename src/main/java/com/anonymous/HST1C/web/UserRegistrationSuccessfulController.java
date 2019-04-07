package com.anonymous.HST1C.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register_successful")
public class UserRegistrationSuccessfulController {
    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(){
        return "register_successful";
    }
}
