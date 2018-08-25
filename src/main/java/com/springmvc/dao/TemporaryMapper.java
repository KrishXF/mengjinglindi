package com.springmvc.dao;

import org.apache.ibatis.annotations.Param;

public interface TemporaryMapper {

    void updateOrder(@Param("orderid") String orderid);

    int getid(@Param("orderid") String orderid);
}
