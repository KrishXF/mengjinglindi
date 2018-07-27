package com.springmvc.service;

import com.springmvc.dto.CardDto;
import com.springmvc.pojo.Card;
import com.springmvc.pojo.OrderDetail;

import java.util.List;

public interface OrderService {

       //获取我的订单列表
       List<OrderDetail> getOrderList(String Adid) throws Exception;

       //新增订单
       int insertOrder(OrderDetail orderDetail)throws Exception;

       int updateOrder(int Id,int orderState)throws Exception;

}
