package com.springmvc.dao;

import com.springmvc.pojo.Zrvcompresstaskassigninfo;

public interface ZrvcompresstaskassigninfoMapper {
    int deleteByPrimaryKey(String recordnum);

    int insert(Zrvcompresstaskassigninfo record);

    int insertSelective(Zrvcompresstaskassigninfo record);

    Zrvcompresstaskassigninfo selectByPrimaryKey(String recordnum);

    int updateByPrimaryKeySelective(Zrvcompresstaskassigninfo record);

    int updateByPrimaryKey(Zrvcompresstaskassigninfo record);
}