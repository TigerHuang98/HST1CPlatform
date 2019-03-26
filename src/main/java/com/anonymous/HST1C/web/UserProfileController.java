package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Login;
import com.anonymous.HST1C.data.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/profile")
public class UserProfileController {
    private LoginRepository loginRepository;
    @Autowired
    public UserProfileController(LoginRepository loginRepository){
        this.loginRepository=loginRepository;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String showUserProfile(
            Model model){
        if(model.containsAttribute("userid")){
            int userid=Integer.parseInt(model.asMap().get("userid").toString());
            Login login=loginRepository.findLoginById(userid);
            model.addAttribute(login);
            return "profile";
        }
        return "profile";
    }
}
