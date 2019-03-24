package com.anonymous.HST1C;

public class User {
    private final long id;
    private String firstName;
    private String lastNmae;
    private String passWord;

    public User(long id,String firstName,String lastNmae,String passWord){
        this.id=id;
        this.firstName=firstName;
        this.lastNmae=lastNmae;
        this.passWord=passWord;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastNmae(String lastNmae) {
        this.lastNmae = lastNmae;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastNmae() {
        return lastNmae;
    }

    public String getPassWord() {
        return passWord;
    }
}
