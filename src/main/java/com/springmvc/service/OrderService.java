package com.springmvc.service;

import com.springmvc.dao.OrderGroupMapper;
import com.springmvc.dto.CardDto;
import com.springmvc.dto.OrderDetailDto;
import com.springmvc.pojo.Card;
import com.springmvc.pojo.OrderDetail;
import com.springmvc.pojo.OrderGroup;

import java.util.List;

public interface OrderService {

       //获取我的订单列表
    //   List<OrderGroup> getOrderList(String Adid) throws Exception;

       List<OrderDetail> getOrderList(String Adid) throws Exception;
       //新增订单
       OrderDetail insertOrder(OrderDetail orderDetail)throws Exception;

       int updateOrder(String Id, int orderState)throws Exception;

       int updateOrderWXCode(String orderId, String WXCode)throws Exception;

       int UpdateOrderGoodsList(String str, OrderDetail card)throws Exception;

       OrderDetail getOrderInfoByWxCode(String wxCode)throws Exception;

       List<OrderDetailDto> selectByIsUsed()throws Exception;

       List<String> insertOrderList(OrderDetail orderDetail) throws Exception;

       OrderGroup insertOrderGroup(OrderGroup orderGroup) throws Exception;

       int updateOrderWXCodeByorderId(List<OrderDetail> orderDetailList) throws Exception;

       int updateOrderByOrderGroupId(String orderGroupId)throws Exception ;

       List<OrderGroup> getOrderGroupList(String Adid) throws Exception;

       int insertOrderWXCode(List<String> codeList, String orderGroupId) throws Exception;

}
