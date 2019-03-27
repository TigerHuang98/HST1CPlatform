package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Userinfo;
import com.anonymous.HST1C.data.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

@Controller
@RequestMapping("/profile")
public class UserProfileController {
    private UserinfoRepository userinfoRepository;
    @Autowired
    public UserProfileController(UserinfoRepository userinfoRepository){
        this.userinfoRepository=userinfoRepository;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String showUserProfile(
            Model model) throws SQLException {
        if(model.containsAttribute("username")){
//            int userid=Integer.parseInt(model.asMap().get("userid").toString());
            String username=model.asMap().get("username").toString();
            Userinfo userinfo=userinfoRepository.findUserinfo(username);
            model.addAttribute(userinfo);
            byte[] imageBytes=userinfo.getIconBytes();
            String imageBase64=Base64.getEncoder().encodeToString(imageBytes);
            model.addAttribute("userIcon",imageBase64);
            //blob.free()?
            return "profile";
        }
        return "profile";//login page?
    }
}
