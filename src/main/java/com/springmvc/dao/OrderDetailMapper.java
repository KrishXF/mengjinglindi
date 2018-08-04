package com.springmvc.dao;

import com.springmvc.dto.OrderDetailDto;
import com.springmvc.pojo.OrderDetail;

import java.util.List;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    List<OrderDetail> selectByAdid(String cardId);

    int updateOrderStatus(OrderDetail record);

    int updateOrderWXCode(OrderDetail record);

    int UpdateOrderGoodsList(List<OrderDetail> orderDetailList);

    int UpdateOrderGoods(OrderDetail orderDetail);

    OrderDetail getOrderInfoByWxCode(String wxcode);

    List<OrderDetailDto> selectByIsUsed();

    int InsertOrderGoodsList(List<OrderDetail> orderDetailList);

    int updateOrderWXCodeByorderId(List<OrderDetail> orderDetailList);

    int updateOrderByOrderGroupId(OrderDetail record);
}