package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Login;
import com.anonymous.HST1C.Role;
import com.anonymous.HST1C.Userinfo;
import com.anonymous.HST1C.data.LoginRepository;
import com.anonymous.HST1C.data.UserinfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;

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
    public String processRegistration(UserRegistrationForm userRegistrationForm, RedirectAttributes model, HttpSession session) throws IOException, SQLException {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String password=userRegistrationForm.getPassword();
        Userinfo userinfo=new Userinfo(
                userRegistrationForm.getUsername(),
                userRegistrationForm.getGender(),
                userRegistrationForm.getPhonenumber(),
                userRegistrationForm.getEmailaddress(),
                userRegistrationForm.getBirthday(),
                new SerialBlob(userRegistrationForm.getIcon().getBytes()));
        Login login=new Login(userRegistrationForm.getUsername(),passwordEncoder.encode(password),Role.CUSTOMER,-1);
        String username=userinfoRepository.addUserinfo(userinfo).getUsername();
        loginRepository.addCustomerLogin(login);
        session.setAttribute("username",username);
        return "redirect:/register_successful";
    }
    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(Model model){
        model.addAttribute(new UserRegistrationForm());
        return "register";
    }
}
