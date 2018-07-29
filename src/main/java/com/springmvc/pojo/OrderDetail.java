package com.springmvc.pojo;

import java.util.Date;

public class OrderDetail {
    private Integer id;

    private String orderid;

    private String adid;

    private String cardid;

    private Integer count;

    private Integer price;

    private Integer totleprice;

    private Integer orderstate;

    private String remarks;

    private Date createtime;

    private Date usingtime;

    private Date overtime;

    private String cardname;

    private Byte cardtype;

    private String detailaddress;

    public Date getGoodsusingtime() {
        return goodsusingtime;
    }

    public void setGoodsusingtime(Date goodsusingtime) {
        this.goodsusingtime = goodsusingtime;
    }

    private Date goodsusingtime;

    private String phone;

    private String name;

    public String getGoodsremark() {
        return goodsremark;
    }

    public void setGoodsremark(String goodsremark) {
        this.goodsremark = goodsremark;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetailaddress() {

        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress;
    }

    private  String goodsremark;

    public String getWxcode() {
        return wxcode;
    }

    public void setWxcode(String wxcode) {
        this.wxcode = wxcode;
    }

    private String wxcode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid == null ? null : adid.trim();
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
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

    public Integer getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(Integer orderstate) {
        this.orderstate = orderstate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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
        this.cardname = cardname == null ? null : cardname.trim();
    }

    public Byte getCardtype() {
        return cardtype;
    }

    public void setCardtype(Byte cardtype) {
        this.cardtype = cardtype;
    }
}