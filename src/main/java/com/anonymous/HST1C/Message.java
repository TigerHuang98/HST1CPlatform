package com.anonymous.HST1C;

import java.sql.Timestamp;

public class Message {
    private int messageid;
    private String text;
    private Timestamp messagedate;
    private int ordernumber;
    private boolean issend;

    public Message(int messageid, String text, Timestamp messagedate, int ordernumber, boolean issend) {
        this.messageid = messageid;
        this.text = text;
        this.messagedate = messagedate;
        this.ordernumber = ordernumber;
        this.issend=issend;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMessagedate(Timestamp messagedate) {
        this.messagedate = messagedate;
    }

    public void setOrdernumber(int ordernumber) {
        this.ordernumber = ordernumber;
    }

    public void setIssend(boolean issend) {
        this.issend = issend;
    }

    public int getMessageid() {
        return messageid;
    }

    public String getText() {
        return text;
    }

    public Timestamp getMessagedate() {
        return messagedate;
    }

    public int getOrdernumber() {
        return ordernumber;
    }

    public boolean isSend() {
        return issend;
    }
}
