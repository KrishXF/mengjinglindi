package com.springmvc.service.impl;

import com.springmvc.dao.OrderDetailMapper;
import com.springmvc.dto.CardDto;
import com.springmvc.pojo.Card;
import com.springmvc.pojo.OrderDetail;
import com.springmvc.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderDetailMapper orderMapper;

    public List<OrderDetail> getOrderList(String Adid) throws Exception{
        List<OrderDetail> orderDetailsList=orderMapper.selectByAdid(Adid);
        return orderDetailsList;

    }
    public int insertOrder(OrderDetail orderDetail) throws Exception{
        orderDetail.setOrderid(UUID.randomUUID().toString().replaceAll("-", ""));
        orderDetail.setOrderstate(0);
        orderDetail.setCreatetime(new Date());
        int result=orderMapper.insert(orderDetail);
        return result;
    }
    public int updateOrder(int Id,int orderState) throws Exception{
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setId(Id);
        orderDetail.setOrderstate(orderState);
        orderDetail.setUsingtime(new Date());
        int result=orderMapper.updateOrderStatus(orderDetail);
        return result;
    }
}
