package com.anonymous.HST1C;

import java.sql.Date;
import java.sql.Timestamp;

public class Order {
    private int odernumber;
    private Timestamp orderdate;
    private String username;
    private int itemid;
    private Status status;
    private Date lostdate;

    public Order(int odernumber, Timestamp orderdate, String username, int itemid, Status status, Date lostdate) {
        this.odernumber = odernumber;
        this.orderdate = orderdate;
        this.username = username;
        this.itemid = itemid;
        this.status = status;
        this.lostdate = lostdate;
    }

    public void setOdernumber(int odernumber) {
        this.odernumber = odernumber;
    }

    public void setOrderdate(Timestamp orderdate) {
        this.orderdate = orderdate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setLostdate(Date lostdate) {
        this.lostdate = lostdate;
    }

    public int getOdernumber() {
        return odernumber;
    }

    public Timestamp getOrderdate() {
        return orderdate;
    }

    public String getUsername() {
        return username;
    }

    public int getItemid() {
        return itemid;
    }

    public Status getStatus() {
        return status;
    }

    public Date getLostdate() {
        return lostdate;
    }
}
