package com.anonymous.HST1C.web;

import com.anonymous.HST1C.User;
import com.anonymous.HST1C.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {
    private UserRepository userRepository;
    @Autowired
    public UserRegistrationController(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(User user, RedirectAttributes model){
        long id=userRepository.addUser(user);
        model.addFlashAttribute("id",id);
        return "redirect:/profile";
    }
    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(){
        return "register";
    }
}
