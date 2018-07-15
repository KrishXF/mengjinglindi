package com.springmvc.dao;

import com.springmvc.pojo.Videomanageplatforminfo;

public interface VideomanageplatforminfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Videomanageplatforminfo record);

    int insertSelective(Videomanageplatforminfo record);

    Videomanageplatforminfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Videomanageplatforminfo record);

    int updateByPrimaryKey(Videomanageplatforminfo record);
}