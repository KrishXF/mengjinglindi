package com.springmvc.dao;


import com.springmvc.pojo.Area;
import com.springmvc.pojo.City;

import java.util.List;
import java.util.Map;

public interface ProvincialUrbanAreaMapper {
    List<Map> getProvinceList();

    List<City> cityByProvinceId(int provinceId);

    List<Area> areaByCityId(int cityId);
}
