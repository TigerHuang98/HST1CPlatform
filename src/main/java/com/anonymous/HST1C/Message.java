package com.anonymous.HST1C;

import java.time.LocalDateTime;

public class Message {
    private int messageid;
    private String text;
    private LocalDateTime messagedate;
    private int ordernumber;
    private boolean issend;

    public Message(int messageid, String text, LocalDateTime messagedate, int ordernumber) {
        this.messageid = messageid;
        this.text = text;
        this.messagedate = messagedate;
        this.ordernumber = ordernumber;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMessagedate(LocalDateTime messagedate) {
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

    public LocalDateTime getMessagedate() {
        return messagedate;
    }

    public int getOrdernumber() {
        return ordernumber;
    }

    public boolean isSend() {
        return issend;
    }
}
