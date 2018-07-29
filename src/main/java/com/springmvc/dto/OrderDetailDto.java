package com.springmvc.dto;

import com.springmvc.pojo.OrderDetail;

public class OrderDetailDto extends OrderDetail {
    public String getGoodsusingtimestr() {
        return goodsusingtimestr;
    }

    public void setGoodsusingtimestr(String goodsusingtimestr) {
        this.goodsusingtimestr = goodsusingtimestr;
    }

    private String  goodsusingtimestr;

}
