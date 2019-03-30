package com.anonymous.HST1C;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;

public class Item {
    private int itemid;
    private Blob picture;
    private BigDecimal price;
    private String itemname;
    private String username;
    private String description;
    private byte[] pictureBytes;

    public Item(int itemid, Blob picture, BigDecimal price, String itemname, String username, String description) {
        this.itemid = itemid;
        this.picture = picture;
        this.price = price;
        this.itemname = itemname;
        this.username = username;
        this.description = description;
    }

    public void readBlobToBytes(){
        try {
            pictureBytes=picture.getBytes(1,(int) picture.length());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getItemid() {
        return itemid;
    }

    public Blob getPicture() {
        return picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getItemname() {
        return itemname;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getPictureBytes() {
        return pictureBytes;
    }
}
