package com.springmvc.controller;

import com.springmvc.common.Result;
import com.springmvc.pojo.Area;
import com.springmvc.pojo.City;
import com.springmvc.service.ProvincialUrbanAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/detail")
public class ProvincialUrbanAreaController {


    @Autowired
    ProvincialUrbanAreaService provincialUrbanAreaService;

    @RequestMapping(value = "/province.do")
    @ResponseBody
    public Result consumeCard(HttpServletRequest resquest, HttpServletResponse response) {
        List<Map> provinceList = provincialUrbanAreaService.getProvinceList();
        return Result.success(provinceList);
    }


    @RequestMapping(value = "/city.do")
    @ResponseBody
    public Result cityByProvinceId(HttpServletRequest request, HttpServletResponse response) {
        int provinceId = Integer.parseInt(request.getParameter("provinceId"));
//        int provinceId = 370000;
        List<City> cityList = provincialUrbanAreaService.cityByProvinceId(provinceId);
        return Result.success(cityList);
    }

    @RequestMapping(value = "/area.do")
    @ResponseBody
    public Result areaByCityId(HttpServletRequest request, HttpServletResponse response) {
        int cityId = Integer.parseInt(request.getParameter("cityId"));
//        int cityId = 120100;
        List<Area> areaList = provincialUrbanAreaService.areaByCityId(cityId);
        return Result.success(areaList);
    }
}
