package com.springmvc.service.impl;

import com.springmvc.dao.CardMapper;
import com.springmvc.dao.OrderDetailMapper;
import com.springmvc.dao.OrderGroupMapper;
import com.springmvc.dto.OrderDetailDto;
import com.springmvc.pojo.Card;
import com.springmvc.pojo.OrderDetail;
import com.springmvc.pojo.OrderGroup;
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

    @Autowired
    private OrderGroupMapper orderGroupMapper;

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
        card= cardMapper.selectCardByCardId(card);
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

    //批量插入卡券组（暂不启用）
    public List<String> insertOrderGroup(OrderGroup orderGroup) throws Exception {
        Card card=new Card();
        card.setCardid(orderGroup.getCardid());
        card= cardMapper.selectCardByCardId(card);
        int inventory=card.getInventory()-orderGroup.getCount();
        if ( inventory<0 ){
            return null;
        }
        int soldnum=card.getSoldnum()+orderGroup.getCount();
        card.setInventory(inventory);
        card.setSoldnum(soldnum);
        //修改剩余卡券数量（预留票）
        cardMapper.updateByPrimaryKeySelective(card);
        String UUid=UUID.randomUUID().toString().replaceAll("-", "");
        orderGroup.setOrdergroupid(UUid);
        orderGroup.setCreatetime(new Date());
        //0未付款 1已付款 2取消订单 3 已领取 4已使用
        orderGroup.setOrderstate(0);
        //创建订单组
        orderGroupMapper.insert(orderGroup);
        //未加入未支付订单概念，返回此数据
        OrderDetail orderDetailfor=new OrderDetail();
        orderDetailfor.setAdid(orderGroup.getAdid());
        orderDetailfor.setCardid(orderGroup.getCardid());
        orderDetailfor.setCount(orderGroup.getCount());
        orderDetailfor.setPrice(orderGroup.getPrice());
        orderDetailfor.setTotleprice(orderGroup.getTotleprice());
        orderDetailfor.setCardname(orderGroup.getCardname());
        orderDetailfor.setCardtype(orderGroup.getCardtype());
        orderDetailfor.setOrderid(UUid);
        orderDetailfor.setOrderstate(1);
        orderDetailfor.setCreatetime(new Date());
        List<String> result= insertOrderList(orderDetailfor);
        return  result;
        //加入未支付订单返回此数据
        // return UUid;
    }

    //批量插入卡券
    public List<String> insertOrderList(OrderDetail orderDetail) throws Exception{
        Card card=new Card();
        card.setCardid(orderDetail.getCardid());
        //获取卡券详情
        card= cardMapper.selectCardByCardId(card);
        int inventory=card.getInventory()-orderDetail.getCount();
        if ( inventory<0 ){
            return null;
        }
        int soldnum=card.getSoldnum()+orderDetail.getCount();
        card.setInventory(inventory);
        card.setSoldnum(soldnum);
        //更新剩余卡券数量
        cardMapper.updateByPrimaryKeySelective(card);

        String groupUUId=UUID.randomUUID().toString().replaceAll("-", "");
        //插入订单列表
        OrderGroup orderGroup=new OrderGroup();
        orderGroup.setAdid(orderDetail.getAdid());
        orderGroup.setCardid(orderDetail.getCardid());
        orderGroup.setCount(orderDetail.getCount());
        orderGroup.setPrice(orderDetail.getPrice());
        int totalprice=orderDetail.getCount()*orderDetail.getPrice();
        orderGroup.setTotleprice(totalprice);
        orderGroup.setCardname(card.getName());
        orderGroup.setCardtype(card.getType());
        orderGroup.setOrdergroupid(groupUUId);
        orderGroup.setOrderstate(1);
        orderGroup.setCreatetime(new Date());
        //创建订单组
        int groupresult=  orderGroupMapper.insert(orderGroup);
        if (groupresult==0 ){
               return null;
        }

        List<String> listUuid=new ArrayList<>();
        List<OrderDetail> orderDetailList=new ArrayList<>();
        for (int i=0;i<orderDetail.getCount();i++){
            String UUid=UUID.randomUUID().toString().replaceAll("-", "");
            OrderDetail orderDetailfor=new OrderDetail();
            orderDetailfor.setAdid(orderDetail.getAdid());
            orderDetailfor.setCardid(orderDetail.getCardid());
            orderDetailfor.setCount(orderDetail.getCount());
            orderDetailfor.setPrice(orderDetail.getPrice());
            orderDetailfor.setRemarks(orderDetailfor.getRemarks());
            orderDetailfor.setCardname(card.getName());
            orderDetailfor.setOrdergroup(groupUUId);
            orderDetail.setCardtype(card.getType()
            );
            orderDetailfor.setOrderid(UUid);
            //正式去掉这一行
            orderDetailfor.setOrderstate(1);
            orderDetailfor.setCreatetime(new Date());
            listUuid.add(UUid);
            orderDetailList.add(orderDetailfor);
            }
        int result=orderMapper.InsertOrderGoodsList(orderDetailList);
        if (result==orderDetail.getCount() ){
            return listUuid;
        }else {
            return null;
        }
    }

    public int updateOrderWXCodeByorderId(List<OrderDetail> orderDetailList) throws Exception{
       return orderMapper.updateOrderWXCodeByorderId(orderDetailList);
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
