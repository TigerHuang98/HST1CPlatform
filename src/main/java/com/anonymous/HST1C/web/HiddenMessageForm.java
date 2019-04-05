package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Role;

public class HiddenMessageForm {
    Role role;
    int ordernumber;
    String text;

    public void setRole(Role role) {
        this.role = role;
    }

    public void setOrdernumber(int ordernumber) {
        this.ordernumber = ordernumber;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Role getRole() {
        return role;
    }

    public int getOrdernumber() {
        return ordernumber;
    }

    public String getText() {
        return text;
    }
}
