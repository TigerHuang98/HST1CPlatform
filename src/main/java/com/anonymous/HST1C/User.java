package com.anonymous.HST1C;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String password;

    public User(){}
    public User(long id, String firstName, String lastName, String password){
        this.id=id;
        this.firstName=firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }
}
