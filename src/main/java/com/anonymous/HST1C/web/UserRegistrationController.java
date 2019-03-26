package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Login;
import com.anonymous.HST1C.Userinfo;
import com.anonymous.HST1C.data.LoginRepository;
import com.anonymous.HST1C.data.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {
    private UserinfoRepository userinfoRepository;
    private LoginRepository loginRepository;
    @Autowired
    public UserRegistrationController(UserinfoRepository userinfoRepository,LoginRepository loginRepository){
        this.userinfoRepository=userinfoRepository;
        this.loginRepository=loginRepository;
    }
    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(Userinfo userinfo,Login login, RedirectAttributes model){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String password=login.getPassword();
        login.setPassword(passwordEncoder.encode(password));
        userinfoRepository.addUserinfo(userinfo);
        int userid=loginRepository.addCustomerLogin(login);
        model.addFlashAttribute("uesrid",userid);
        return "redirect:/profile";
    }
    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(){
        return "register";
    }
}
