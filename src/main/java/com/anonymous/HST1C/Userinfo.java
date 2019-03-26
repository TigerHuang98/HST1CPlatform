package com.anonymous.HST1C;

import java.sql.Blob;
import java.sql.Date;

public class Userinfo {
    private String username;
    private Gender gender;
    private String phonenumber;
    private String emailaddress;
    private Date birthdate;
    private Blob icon;

    public Userinfo(){

    }
    public Userinfo(String username,Gender gender,String phonenumber,String emailaddress,Date birthdate,Blob icon){
        this.username=username;
        this.gender=gender;
        this.phonenumber=phonenumber;
        this.emailaddress=emailaddress;
        this.birthdate=birthdate;
        this.icon=icon;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setIcon(Blob icon) {
        this.icon = icon;
    }

    public String getUsername() {
        return username;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public Blob getIcon() {
        return icon;
    }
}
