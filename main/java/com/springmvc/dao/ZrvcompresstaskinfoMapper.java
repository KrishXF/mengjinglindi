package com.springmvc.dao;

import com.springmvc.pojo.Zrvcompresstaskinfo;

public interface ZrvcompresstaskinfoMapper {
    int deleteByPrimaryKey(String recordnum);

    int insert(Zrvcompresstaskinfo record);

    int insertSelective(Zrvcompresstaskinfo record);

    Zrvcompresstaskinfo selectByPrimaryKey(String recordnum);

    int updateByPrimaryKeySelective(Zrvcompresstaskinfo record);

    int updateByPrimaryKey(Zrvcompresstaskinfo record);
}