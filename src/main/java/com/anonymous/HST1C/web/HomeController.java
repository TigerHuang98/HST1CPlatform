package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Login;
import com.anonymous.HST1C.data.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController {
    private LoginRepository loginRepository;

    @Autowired
    public HomeController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model){
        model.addAttribute(new LoginForm());
        return "home";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String processLogin(LoginForm loginForm,RedirectAttributes model){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        Login login=loginRepository.findLoginByUsername(loginForm.getUsername());
        if(
                login!=null
            &&
                passwordEncoder.matches(loginForm.getPassword(),login.getPassword())
        ){
            model.addFlashAttribute("username",login.getUsername());
            return "redirect:/profile";
        }else{
            return "home";//TODO:login failed logic?
        }
    }
}
