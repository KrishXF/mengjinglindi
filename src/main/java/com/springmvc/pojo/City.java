package com.springmvc.pojo;

public class City {

    private Integer id;
    private Integer cityId;
    private String city;
    private Integer provinceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public City() {
    }

    public City(Integer id, Integer cityId, String city, Integer provinceId) {
        this.id = id;
        this.cityId = cityId;
        this.city = city;
        this.provinceId = provinceId;
    }
}
