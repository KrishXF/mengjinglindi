package com.springmvc.service.impl;

import com.springmvc.dao.CardMapper;
import com.springmvc.dao.OrderDetailMapper;
import com.springmvc.dao.TemporaryMapper;
import com.springmvc.pojo.Card;
import com.springmvc.pojo.OrderDetail;
import com.springmvc.service.TemporaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
public class TemporaryServiceImpl implements TemporaryService {
    @Autowired
    private OrderDetailMapper orderMapper;

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private TemporaryMapper temporaryMapper;


    public String insertOrder(OrderDetail orderDetail) throws Exception {
        String UUid = UUID.randomUUID().toString().replaceAll("-", "");
        orderDetail.setOrderid(UUid);
        orderDetail.setOrderstate(-1);
        orderDetail.setCreatetime(new Date());
        int result = orderMapper.insert(orderDetail);
        if (result == 0) {
            return "";
        }
        Card card = new Card();
        card.setCardid(orderDetail.getCardid());
        card = cardMapper.selectCardByCardId(card);
        int inventory = card.getInventory() - orderDetail.getCount();
        if (inventory < 0) {
            return "";
        }
        int soldnum = card.getSoldnum() + orderDetail.getCount();
        card.setInventory(inventory);
        card.setSoldnum(soldnum);
        result = cardMapper.updateByPrimaryKeySelective(card);
        if (result == 0) {
            return "";
        } else {
            return UUid;
        }
    }

    @Override
    public void updateOrder(String orderid) {
        temporaryMapper.updateOrder(orderid);
    }

    @Override
    public int getid(String orderid) {
        int id = temporaryMapper.getid(orderid);
        return id;
    }
}
