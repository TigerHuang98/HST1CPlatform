package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Gender;
import com.anonymous.HST1C.Login;
import com.anonymous.HST1C.Role;
import com.anonymous.HST1C.Userinfo;
import com.anonymous.HST1C.data.LoginRepository;
import com.anonymous.HST1C.data.UserinfoRepository;
import org.h2.jdbc.JdbcBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;

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
    public String processRegistration(UserRegistrationForm userRegistrationForm){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String password=userRegistrationForm.getPassword();
        Userinfo test=new Userinfo();
        test.setBirthdate(userRegistrationForm.getBirthday());
//        Userinfo userinfo=new Userinfo(userRegistrationForm.getUsername(),userRegistrationForm.getGender(),userRegistrationForm.getEmailaddress(),userRegistrationForm.getBirthday(),(Blob)null);
        Login login=new Login(userRegistrationForm.getUsername(),passwordEncoder.encode(password),Role.CUSTOMER,-1);
        login.setUserid(-1);



//        userinfoRepository.addUserinfo(userinfo);
//        int userid=loginRepository.addCustomerLogin(login);
//        model.addFlashAttribute("uesrid",userid);
        return "redirect:/profile";
    }
    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(Model model){
        model.addAttribute(new UserRegistrationForm());
        return "register";
    }
}
