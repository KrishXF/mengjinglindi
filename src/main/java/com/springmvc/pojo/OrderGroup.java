package com.springmvc.pojo;

import java.util.Date;

public class OrderGroup {
    private Integer id;

    private String ordergroupid;

    private String adid;

    private String cardid;

    private Integer orderstate;

    private Integer count;

    private Integer price;

    private Integer totleprice;

    private Date createtime;

    private Date usingtime;

    private Date overtime;

    private String cardname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdergroupid() {
        return ordergroupid;
    }

    public void setOrdergroupid(String ordergroupid) {
        this.ordergroupid = ordergroupid;
    }

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public Integer getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(Integer orderstate) {
        this.orderstate = orderstate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotleprice() {
        return totleprice;
    }

    public void setTotleprice(Integer totleprice) {
        this.totleprice = totleprice;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUsingtime() {
        return usingtime;
    }

    public void setUsingtime(Date usingtime) {
        this.usingtime = usingtime;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public Integer getCardtype() {
        return cardtype;
    }

    public void setCardtype(Integer cardtype) {
        this.cardtype = cardtype;
    }

    private Integer cardtype;

}