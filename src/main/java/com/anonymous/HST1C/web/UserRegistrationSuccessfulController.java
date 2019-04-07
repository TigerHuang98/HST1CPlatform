package com.anonymous.HST1C.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/register_successful")
public class UserRegistrationSuccessfulController {
    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(Model model, HttpSession session){
        model.addAttribute("role",session.getAttribute("role"));
        return "register_successful";
    }
}
