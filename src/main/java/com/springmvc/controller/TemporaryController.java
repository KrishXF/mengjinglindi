package com.springmvc.controller;


import com.springmvc.common.CodeMsg;
import com.springmvc.common.Result;
import com.springmvc.pojo.OrderDetail;
import com.springmvc.service.OrderService;
import com.springmvc.service.TemporaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

@RequestMapping("/temporary")
public class TemporaryController {

    @Autowired
    OrderService orderService;

    @Autowired
    TemporaryService temporaryService;

    //新增订单（点击购买，新增订单，未付款）--wsx
    @RequestMapping("/insertOrder.do")
    @ResponseBody
    public Result insertOrder(OrderDetail orderDetail, HttpServletRequest request, HttpServletResponse response) {
        try {
            String resultdata = temporaryService.insertOrder(orderDetail);
            int id = temporaryService.getid(resultdata);
            System.out.println("=====resultdata:"+resultdata);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderid", resultdata);
            map.put("id", id);
            if (resultdata != "") {
                return Result.success(map);
            } else {
                return Result.error(CodeMsg.Failed);
            }
        } catch (Exception ex) {
            return Result.error(CodeMsg.Failed, ex.getMessage());
        }
    }

    @RequestMapping("/updateOrder.do")
    public Result updateOrder(HttpServletRequest request, HttpServletResponse response) {
        String orderid = request.getParameter("orderid");
        temporaryService.updateOrder(orderid);
        return Result.success();
    }


}
