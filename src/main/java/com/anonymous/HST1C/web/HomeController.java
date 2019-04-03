package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Login;
import com.anonymous.HST1C.Role;
import com.anonymous.HST1C.data.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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
    public String processLogin(LoginForm loginForm, RedirectAttributes model, HttpSession session){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        Login login=loginRepository.findLoginByUsername(loginForm.getUsername());
        if(
                login!=null
            &&
                passwordEncoder.matches(loginForm.getPassword(),login.getPassword())
        ){
            String username=login.getUsername();
            session.setAttribute("username",username);
            model.addFlashAttribute("username",username);
            switch (login.getUid()){
                case CUSTOMER:
                    return "redirect:/claim_list";
                case STAFF:
                    return "redirect:/staff_list";
                default:
                    return "home";
            }
        }else{
            return "home";//TODO:login failed logic?
        }
    }
}
