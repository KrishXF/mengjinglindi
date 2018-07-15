package com.springmvc.pojo;

public class Zrvdeviceinfo {
    private Integer id;

    private String deviceip;

    private Integer status;

    private Integer expires;

    private Integer resved1;

    private String resved2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceip() {
        return deviceip;
    }

    public void setDeviceip(String deviceip) {
        this.deviceip = deviceip == null ? null : deviceip.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    public Integer getResved1() {
        return resved1;
    }

    public void setResved1(Integer resved1) {
        this.resved1 = resved1;
    }

    public String getResved2() {
        return resved2;
    }

    public void setResved2(String resved2) {
        this.resved2 = resved2 == null ? null : resved2.trim();
    }
}