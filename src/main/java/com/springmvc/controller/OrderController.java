package com.springmvc.controller;

import com.springmvc.common.CodeMsg;
import com.springmvc.common.Result;
import com.springmvc.dto.CardDto;
import com.springmvc.dto.OrderDetailDto;
import com.springmvc.pojo.Card;
import com.springmvc.pojo.OrderDetail;
import com.springmvc.pojo.OrderGroup;
import com.springmvc.service.OrderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final static Logger logger =LoggerFactory.getLogger(OrderController.class);
    @Autowired
    OrderService orderservice;

    //根据用户获取订单列表--wsx
    @RequestMapping("/getOderList.do")
    @ResponseBody
    public Result getOrderList(HttpServletRequest request, HttpServletResponse response){
        try {
            String adid=URLDecoder.decode(request.getParameter("Adid"), "utf-8") ;
            return Result.success(orderservice.getOrderList(adid));
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  Result.error(CodeMsg.Failed,ex.getMessage());
        }
    }

    //新增订单（点击购买，新增订单，未付款）--wsx
    @RequestMapping("/insertOrder.do")
    @ResponseBody
    public Result insertOrder(OrderDetail orderDetail, HttpServletRequest request, HttpServletResponse response){
        try {
            String resultdata=orderservice.insertOrder(orderDetail);
            if (resultdata!=""){
                return Result.success(resultdata);
            }else {
                return Result.error(CodeMsg.Failed);
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  Result.error(CodeMsg.Failed,ex.getMessage());
        }
    }

    //批量插入卡券
    @RequestMapping("/insertOrderList.do")
    @ResponseBody
    public Result insertOrderList(OrderDetail orderDetail, HttpServletRequest request, HttpServletResponse response){
        try {
            List<String> resultdata=orderservice.insertOrderList(orderDetail);
            if (resultdata!=null){
                return Result.success(resultdata);
            }else {
                return Result.error(CodeMsg.Failed);
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  Result.error(CodeMsg.Failed,ex.getMessage());
        }
    }

    @RequestMapping("/insertOrderGroup.do")
    @ResponseBody
    public Result insertOrderGroup(OrderGroup orderGroup, HttpServletRequest request, HttpServletResponse response){
        try {
            List<String> resultdata=orderservice.insertOrderGroup(orderGroup);
            if (resultdata!=null){
                return Result.success(resultdata);
            }else {
                return Result.error(CodeMsg.Failed);
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  Result.error(CodeMsg.Failed,ex.getMessage());
        }
    }

    //根据为订单批量添加WSCode
    @RequestMapping("/updateOrderWXCodeByorderId.do")
    @ResponseBody
    public Result updateOrderWXCodeByorderId(@RequestParam("postdata") String postdata, HttpServletRequest request, HttpServletResponse response){
        try {
            org.json.JSONObject json = new org.json.JSONObject(postdata);
            String orderList = json.getString("orderList");
//            org.json.JSONObject json1 = new org.json.JSONObject(age);
            JSONArray jsonArray = JSONArray.fromObject(orderList);
            List<OrderDetail> orderDetailList=new ArrayList<>();
            for ( int i=0;i<jsonArray.size();i++){
                JSONObject job = jsonArray.getJSONObject(i);
                OrderDetail orderdetail=new OrderDetail();
                orderdetail.setOrderid(job.get("orderid").toString());
                orderdetail.setWxcode(job.get("wscode").toString());
                orderDetailList.add(orderdetail);
            }
            int result=orderservice.updateOrderWXCodeByorderId(orderDetailList);
            if (result==jsonArray.size()){
                return Result.success();
            }else{
                return Result.error(CodeMsg.Failed);
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  Result.error(CodeMsg.Failed,ex.getMessage());
        }
    }

    //更新订单
    @RequestMapping("/updateOrder.do")
    @ResponseBody
    public Result updateOrder(HttpServletRequest request, HttpServletResponse response){
        try {
            int Id= Integer.parseInt(URLDecoder.decode(request.getParameter("Id"), "utf-8")) ;
            int orderState=Integer.parseInt(URLDecoder.decode(request.getParameter("OrderState"), "utf-8")) ;
            int resultdata=orderservice.updateOrder(Id,orderState);
            if ( resultdata==1 ){
                return Result.success();
            }else {
                return Result.error(CodeMsg.Failed);
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  Result.error(CodeMsg.Failed,ex.getMessage());
        }
    }

    @RequestMapping("/updateOrderWXCode.do")
    @ResponseBody
    public Result updateOrderWXCode(HttpServletRequest request, HttpServletResponse response){
        try {
            String orderId= URLDecoder.decode(request.getParameter("orderId"), "utf-8") ;
            String WXCode=URLDecoder.decode(request.getParameter("WXCode"), "utf-8");
            int resultdata=orderservice.updateOrderWXCode(orderId,WXCode);
            if ( resultdata==1 ){
                return Result.success();
            }else {
                return Result.error(CodeMsg.Failed);
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  Result.error(CodeMsg.Failed,ex.getMessage());
        }
    }

    @RequestMapping("/updateOrderGoodsList.do")
    @ResponseBody
    public Result updateOrderGoodsList(String str, OrderDetail orderDetail, HttpServletRequest request, HttpServletResponse response){

        try {
            int resultdata=orderservice.UpdateOrderGoodsList(str,orderDetail);
            if ( resultdata==1 ){
                return Result.success();
            }else {
                return Result.error(CodeMsg.Failed);
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  Result.error(CodeMsg.Failed,ex.getMessage());
        }
    }

    @RequestMapping("/orderRedirect.do")
    public void orderRedirect(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String id=URLDecoder.decode(request.getParameter("id"), "utf-8")  ;
        response.sendRedirect( "../page/itemcontent_my.html?id="+id);
    }

    @RequestMapping("/getOrderInfoByWxCode.do")
    @ResponseBody
    public Result getOrderInfoByWxCode( HttpServletRequest request, HttpServletResponse response){
        try {
            String WXCode=URLDecoder.decode(request.getParameter("WXCode"), "utf-8");
            OrderDetail resultdata=orderservice.getOrderInfoByWxCode(WXCode);
            return Result.success(resultdata);

        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  Result.error(CodeMsg.Failed,ex.getMessage());
        }
    }
    @RequestMapping("/selectByIsUsed.do")
    @ResponseBody
    public List<OrderDetailDto> selectByIsUsed(HttpServletRequest request, HttpServletResponse response){
        try {
            return orderservice.selectByIsUsed();
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

}
