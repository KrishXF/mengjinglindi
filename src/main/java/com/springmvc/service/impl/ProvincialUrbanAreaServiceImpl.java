package com.springmvc.service.impl;


import com.springmvc.dao.ProvincialUrbanAreaMapper;
import com.springmvc.pojo.Area;
import com.springmvc.pojo.City;
import com.springmvc.service.ProvincialUrbanAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ProvincialUrbanAreaServiceImpl implements ProvincialUrbanAreaService {

    @Resource
    ProvincialUrbanAreaMapper provincialUrbanAreaMapper;


    @Override
    public List<Map> getProvinceList() {
        List<Map> provinceList = provincialUrbanAreaMapper.getProvinceList();
        return provinceList;

    }

    @Override
    public List<City> cityByProvinceId(int provinceId) {
        System.out.println(provinceId);
        List<City> cityList = provincialUrbanAreaMapper.cityByProvinceId(provinceId);
        return cityList;
    }

    @Override
    public List<Area> areaByCityId(int cityId) {
        List<Area> areaList = provincialUrbanAreaMapper.areaByCityId(cityId);
        System.out.println(areaList);
        return areaList;
    }
}
