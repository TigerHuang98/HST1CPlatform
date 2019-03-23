package com.anonymous.HST1C;

public class User {
    private final long id;
    private String firstName;
    private String lastNmae;
    private String passWord;

    public User(long id){
        this.id=id;
    }

    public long getId() {
        return id;
    }
}
