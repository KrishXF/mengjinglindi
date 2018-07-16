package com.springmvc.dao;

import com.springmvc.pojo.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    int getType(Admin admin);

    void getMap(@Param("name") String name, @Param("password") String password);

    Admin checkInfo(String name);
}