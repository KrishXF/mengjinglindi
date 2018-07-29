package com.springmvc.service;

import com.springmvc.dto.CardDto;
import com.springmvc.dto.OrderDetailDto;
import com.springmvc.pojo.Card;
import com.springmvc.pojo.OrderDetail;

import java.util.List;

public interface OrderService {

       //获取我的订单列表
       List<OrderDetail> getOrderList(String Adid) throws Exception;

       //新增订单
       String insertOrder(OrderDetail orderDetail)throws Exception;

       int updateOrder(int Id, int orderState)throws Exception;

       int updateOrderWXCode(String orderId, String WXCode)throws Exception;

       int UpdateOrderGoodsList(String str, OrderDetail card)throws Exception;

       OrderDetail getOrderInfoByWxCode(String wxCode)throws Exception;

       List<OrderDetailDto> selectByIsUsed()throws Exception;
}
