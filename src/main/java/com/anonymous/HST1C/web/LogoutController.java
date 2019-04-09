package com.anonymous.HST1C.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    @RequestMapping(method = RequestMethod.GET)
    public String processLogout(HttpSession session){
        session.removeAttribute("username");
        session.removeAttribute("role");
        return "redirect:/";
    }
}
