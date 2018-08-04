package com.springmvc.dao;


import com.springmvc.pojo.OrderGroup;

import java.util.List;

public interface OrderGroupMapper {

    int insert(OrderGroup record);

    int updatestatus(OrderGroup record);

    List<OrderGroup> selectByAdid(String cardId);

    int updateOrderByOrderGroupId(OrderGroup record);
}