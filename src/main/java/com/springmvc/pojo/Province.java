package com.springmvc.pojo;

public class Province {
    private Integer id;
    private Integer provinceId;
    private String province;
    private Integer type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Province(Integer id, Integer provinceId, String province, Integer type) {
        this.id = id;
        this.provinceId = provinceId;
        this.province = province;
        this.type = type;
    }

    public Province() {
    }
}
