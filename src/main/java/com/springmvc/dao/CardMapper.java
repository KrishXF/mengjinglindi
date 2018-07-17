package com.springmvc.dao;

import com.springmvc.pojo.Card;

public interface CardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Card record);

    int insertSelective(Card record);

    Card selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKeyWithBLOBs(Card record);

    int updateByPrimaryKey(Card record);
}