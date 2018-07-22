package com.springmvc.pojo;

import java.util.Date;

public class Card {
    private Integer id;

    private Integer cardid;

    private String name;

    private Integer price;

    private String img;

    private Integer timestrap;

    private Byte type;

    private Integer inventory;

    private Integer soldnum;

    private Integer cardstate;

    private String remarks;

    private Date startdate;

    private Date enddate;

    private String timeremarks;

    private String introduc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardid() {
        return cardid;
    }

    public void setCardid(Integer cardid) {
        this.cardid = cardid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getTimestrap() {
        return timestrap;
    }

    public void setTimestrap(Integer timestrap) {
        this.timestrap = timestrap;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getSoldnum() {
        return soldnum;
    }

    public void setSoldnum(Integer soldnum) {
        this.soldnum = soldnum;
    }

    public Integer getCardstate() {
        return cardstate;
    }

    public void setCardstate(Integer cardstate) {
        this.cardstate = cardstate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getTimeremarks() {
        return timeremarks;
    }

    public void setTimeremarks(String timeremarks) {
        this.timeremarks = timeremarks;
    }

    public String getIntroduc() {
        return introduc;
    }

    public void setIntroduc(String introduc) {
        this.introduc = introduc;
    }


}