package com.springmvc.controller;

import com.springmvc.common.CodeMsg;
import com.springmvc.common.Result;
import com.springmvc.dto.OrderDetailDto;
import com.springmvc.pojo.Card;
import com.springmvc.pojo.OrderDetail;
import com.springmvc.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final static Logger logger =LoggerFactory.getLogger(OrderController.class);
    @Autowired
    OrderService orderservice;

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
