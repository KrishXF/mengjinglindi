package com.springmvc.pojo;

public class Area {

    private Integer id;
    private Integer areaId;
    private String area;
    private Integer cityId;
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Area(Integer id, Integer areaId, String area, Integer cityId, Integer type) {
        this.id = id;
        this.areaId = areaId;
        this.area = area;
        this.cityId = cityId;
        this.type = type;
    }

    public Area() {
    }
}
