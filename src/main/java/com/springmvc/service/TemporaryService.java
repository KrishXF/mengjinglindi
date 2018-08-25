package com.springmvc.service;

import com.springmvc.pojo.OrderDetail;

public interface TemporaryService {

    String insertOrder(OrderDetail orderDetail) throws Exception;

    void updateOrder(String orderid);

    int getid(String orderid);

}
