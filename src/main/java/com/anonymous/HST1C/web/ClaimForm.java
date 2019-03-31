package com.anonymous.HST1C.web;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Date;

public class ClaimForm {
    private MultipartFile picture;
    private BigDecimal price;
    private String itemname;
    private String description;
    private Date lostdate;

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLostdate(Date lostdate) {
        this.lostdate = lostdate;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getItemname() {
        return itemname;
    }

    public String getDescription() {
        return description;
    }

    public Date getLostdate() {
        return lostdate;
    }
}
