package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Gender;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

public class UserRegistrationForm {

    //IMPORTANT: Don't forget to change the Validation error messages if there is any change on the constraints

    @NotNull
    @Size(min=4, max=30)
    private String username;

    @NotNull
    @Size(min=8, max=30)
    private String password;

    @NotNull
    private Gender gender;

    @NotNull
    @Pattern(regexp="^\\+?(?:[0-9] ?){6,14}[0-9]$")
    private String phonenumber;

    @NotNull
    @Pattern(regexp="^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$")
    private String emailaddress;

    @NotNull
    @Past
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
