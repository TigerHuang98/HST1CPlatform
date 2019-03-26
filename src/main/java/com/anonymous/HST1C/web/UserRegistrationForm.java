package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Gender;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

public class UserRegistrationForm {
    private String username;
    private String password;
    private Gender gender;
    private String phonenumber;
    private String emailaddress;
    private Date birthday;
    private MultipartFile icon;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setIcon(MultipartFile icon) {
        this.icon = icon;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public Date getBirthday() {
        return birthday;
    }

    public MultipartFile getIcon() {
        return icon;
    }
}
