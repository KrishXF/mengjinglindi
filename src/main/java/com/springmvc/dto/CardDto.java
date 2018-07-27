package com.springmvc.dto;

import com.springmvc.pojo.Card;

import java.math.BigDecimal;

public class CardDto extends Card {

    private String enddatestring;

    public String getStartdatestring() {
        return startdatestring;
    }

    public void setStartdatestring(String startdatestring) {
        this.startdatestring = startdatestring;
    }

    public String getTimestrapString() {
        return timestrapString;
    }

    public void setTimestrapString(String timestrapString) {
        this.timestrapString = timestrapString;
    }

    private  String startdatestring;

    private String timestrapString;


    private Double doublePrice;

    public Double getDoublePrice() {
        return doublePrice;
    }

    public void setDoublePrice(Double doublePrice) {
        this.doublePrice = doublePrice;
    }

    public String getEnddatestring() {
        return enddatestring;
    }

    public void setEnddatestring(String enddatestring) {
        this.enddatestring = enddatestring;
    }
}
