package com.springmvc.service.impl;

import com.springmvc.dao.CardMapper;
import com.springmvc.dao.OrderDetailMapper;
import com.springmvc.dto.OrderDetailDto;
import com.springmvc.pojo.Card;
import com.springmvc.pojo.OrderDetail;
import com.springmvc.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderDetailMapper orderMapper;

    @Autowired
    private CardMapper cardMapper;

    public List<OrderDetail> getOrderList(String Adid) throws Exception{
        List<OrderDetail> orderDetailsList=orderMapper.selectByAdid(Adid);
        return orderDetailsList;

    }
    public String insertOrder(OrderDetail orderDetail) throws Exception{
        String UUid=UUID.randomUUID().toString().replaceAll("-", "");
        orderDetail.setOrderid(UUid);
        orderDetail.setOrderstate(0);
        orderDetail.setCreatetime(new Date());
        int result=orderMapper.insert(orderDetail);
        if (result==0){
            return "";
        }
        Card card=new Card();
        card.setCardid(orderDetail.getCardid());
        card= cardMapper.selectCarByCardId(card);
        int inventory=card.getInventory()-orderDetail.getCount();
        if ( inventory<0 ){
            return "";
        }
        int soldnum=card.getSoldnum()+orderDetail.getCount();
        card.setInventory(inventory);
        card.setSoldnum(soldnum);
        result=cardMapper.updateByPrimaryKeySelective(card);
        if ( result==0 ){
            return "";
        }else {
            return UUid  ;
        }
    }
    public int updateOrder(int Id,int orderState) throws Exception{
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setId(Id);
        orderDetail.setOrderstate(orderState);
        orderDetail.setUsingtime(new Date());
        int result=orderMapper.updateOrderStatus(orderDetail);
        return result;
    }

    public int updateOrderWXCode(String orderId,String WXCode) throws Exception{
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setOrderid(orderId);
        orderDetail.setWxcode(WXCode);
        int result=orderMapper.updateOrderWXCode(orderDetail);
        return result;

    }
    public int UpdateOrderGoodsList(String str,OrderDetail orderDetail)throws Exception{
        List<OrderDetail> orderDetailList=new ArrayList<>();
        orderDetail.setGoodsusingtime(new Date());
        orderDetail.setOrderstate(2);
        int result=0;
        String[]  stringList= str.split(",");
        for (String s:stringList
             ) {
            orderDetail.setWxcode(s);
          //  orderDetailList.add(orderDetail);
             result=orderMapper.UpdateOrderGoods(orderDetail);
        }
       // result=orderMapper.UpdateOrderGoodsList(orderDetailList);
        return  result;
    }
    public OrderDetail getOrderInfoByWxCode(String wxCode)throws Exception{
        return   orderMapper.getOrderInfoByWxCode(wxCode);

    }

    public List<OrderDetailDto> selectByIsUsed() throws Exception{
        List<OrderDetailDto> orderDetailsList=orderMapper.selectByIsUsed();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (OrderDetailDto odt:orderDetailsList
             ) {
            Date time=odt.getGoodsusingtime();
            odt.setGoodsusingtimestr(df.format((time)));
        }
        return orderDetailsList;

    }
}
