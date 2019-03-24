package com.anonymous.HST1C.web;

import com.anonymous.HST1C.User;
import com.anonymous.HST1C.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/profile")
public class UserProfileController {
    private UserRepository userRepository;
    @Autowired
    public UserProfileController(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String showUserProfile(
            Model model){
        if(model.containsAttribute("id")){
            long id=Long.parseLong(model.asMap().get("id").toString());
            User user= userRepository.findUser(id);
            model.addAttribute(user);
            return "profile";
        }
        return "profile";
    }
}
