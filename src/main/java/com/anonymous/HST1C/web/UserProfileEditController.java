package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Login;
import com.anonymous.HST1C.Role;
import com.anonymous.HST1C.Userinfo;
import com.anonymous.HST1C.data.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

@Controller
@RequestMapping("/profile_edit")
public class UserProfileEditController {
    private UserinfoRepository userinfoRepository;
    @Autowired
    public UserProfileEditController(UserinfoRepository userinfoRepository){
        this.userinfoRepository=userinfoRepository;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String getUserProfile(
            Model model,HttpSession session) throws SQLException {
        model.addAttribute(new UserUpdateForm());
        Object usernameAttribute=session.getAttribute("username");
        if(usernameAttribute!=null){
            Userinfo userinfo = userinfoRepository.findUserinfo(usernameAttribute.toString());
            model.addAttribute(userinfo);
            byte[] imageBytes = userinfo.getIconBytes();
            String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
            model.addAttribute("userIcon", imageBase64);
        }else {
            if (model.containsAttribute("username")) {
//            int userid=Integer.parseInt(model.asMap().get("userid").toString());
                String username = model.asMap().get("username").toString();
                Userinfo userinfo = userinfoRepository.findUserinfo(username);
                model.addAttribute(userinfo);
                byte[] imageBytes = userinfo.getIconBytes();
                String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                model.addAttribute("userIcon", imageBase64);
                return "profile_edit";
            }
        }
        model.addAttribute("role",session.getAttribute("role"));
        return "profile_edit";//login page?
    }
    @RequestMapping(method = RequestMethod.POST)
    public String editUserProfile(@Valid UserUpdateForm userUpdateForm, Errors errors, Model model, HttpSession session) throws IOException, SQLException {
        Userinfo userinfo = userinfoRepository.findUserinfo(session.getAttribute("username").toString());
        model.addAttribute(userinfo);
        byte[] imageBytes = userinfo.getIconBytes();
        String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
        model.addAttribute("userIcon", imageBase64);
        if(userUpdateForm.getBirthday()==null){
            model.addAttribute("birthday_not_set","");
            return "profile_edit";
        }
        if(userUpdateForm.getIcon().getOriginalFilename().equals("")){
            model.addAttribute("icon_not_set","");
            return "profile_edit";
        }
        if(errors.hasErrors()){
            return "profile_edit";
        }
//        if(userinfoRepository.findUserinfo(userUpdateForm.getUsername())!=null){
//            model.addAttribute("duplicate_user","");
//            return "register";
//        }
//        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
//        String password=userUpdateForm.getPassword();
        Userinfo updatedUserinfo=new Userinfo(
                session.getAttribute("username").toString(),
                userUpdateForm.getGender(),
                userUpdateForm.getPhonenumber(),
                userUpdateForm.getEmailaddress(),
                userUpdateForm.getBirthday(),
                new SerialBlob(userUpdateForm.getIcon().getBytes()));
//        Login login=new Login(userUpdateForm.getUsername(),passwordEncoder.encode(password), Role.CUSTOMER,-1);
        String username=userinfoRepository.updateUserinfo(updatedUserinfo).getUsername();
//        loginRepository.addCustomerLogin(login);
//        session.setAttribute("username",username);
//        switch (login.getUid()){
//            case CUSTOMER:
//                session.setAttribute("role",Role.CUSTOMER);
//                break;
//            case STAFF:
//                session.setAttribute("role",Role.STAFF);
//                break;
//        }
        ((BindingAwareModelMap) model).clear();
        return "redirect:/profile";
    }
}
