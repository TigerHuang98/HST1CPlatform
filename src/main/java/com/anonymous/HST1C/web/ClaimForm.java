package com.anonymous.HST1C.web;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;

public class ClaimForm {

    //IMPORTANT: Don't forget to change the Validation error messages if there is any change on the constraints

    private MultipartFile picture;
    @DecimalMin("0")
    private BigDecimal price;
    @NotNull
    @Size(min=1)
    private String itemname;
    private String description;
    @NotNull
    @Past
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
