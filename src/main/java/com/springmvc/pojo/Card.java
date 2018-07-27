package com.springmvc.pojo;

import java.util.Date;

public class Card {
    private Integer id;

    private String cardid;

    private String name;

    private Integer price;

    private String img;

    private Date timestrap;

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

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        this.img = img == null ? null : img.trim();
    }

    public Date getTimestrap() {
        return timestrap;
    }

    public void setTimestrap(Date timestrap) {
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
        this.remarks = remarks == null ? null : remarks.trim();
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
        this.timeremarks = timeremarks == null ? null : timeremarks.trim();
    }

    public String getIntroduc() {
        return introduc;
    }

    public void setIntroduc(String introduc) {
        this.introduc = introduc == null ? null : introduc.trim();
    }
}