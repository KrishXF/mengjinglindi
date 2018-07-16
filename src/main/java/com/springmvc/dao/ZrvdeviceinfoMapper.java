package com.springmvc.dao;

import com.springmvc.pojo.Zrvdeviceinfo;

public interface ZrvdeviceinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Zrvdeviceinfo record);

    int insertSelective(Zrvdeviceinfo record);

    Zrvdeviceinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Zrvdeviceinfo record);

    int updateByPrimaryKey(Zrvdeviceinfo record);
}