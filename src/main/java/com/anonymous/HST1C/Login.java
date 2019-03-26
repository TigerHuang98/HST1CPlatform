package com.anonymous.HST1C;

public class Login {
    private String username;
    private String password;
    private Role uid;
    private int userid;

    public Login(){

    }

    public Login(String username, String password, Role uid, int userid) {
        this.username = username;
        this.password = password;
        this.uid = uid;
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUid(Role uid) {
        this.uid = uid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getUid() {
        return uid;
    }

    public int getUserid() {
        return userid;
    }
}
