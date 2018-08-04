package com.springmvc.controller;

import com.springmvc.common.CodeMsg;
import com.springmvc.common.Result;
import com.springmvc.config.WxMpConfig;
import com.springmvc.service.UserService;
import com.springmvc.service.WeixinService;
import com.springmvc.util.CheckoutUtil;
import com.springmvc.util.HttpGetUtil;
import com.springmvc.util.SessionUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserService userService;

    @Autowired
    WxMpConfig wxMpConfig;

    @Autowired
    WeixinService weixinService;

    @RequestMapping("/getInfo")
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) {

        boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter print;
        if (isGet) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
                try {
                    print = response.getWriter();
                    print.write(echostr);
                    print.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/getOpenID")
    public void getOpenID(HttpServletRequest request, HttpServletResponse response) {
        try {
            String code = request.getParameter("code");
            String url = "appid=wxaf935dfd17fe3962&secret=0d5d90db193001085a9788f19c8c0490&code=" + code + "&grant_type=authorization_code";
            String aaa = HttpGetUtil.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", url);
            JSONObject aaaa = JSONObject.fromObject(aaa);
            String openid = aaaa.get("openid").toString();
            openid.substring(1, openid.length() - 1);
            SessionUtil.setParam(request, "openID", openid);
            logger.info("session:" + openid);
            response.sendRedirect("../page/itemlist.html");
        } catch (IOException e) {
            logger.error("Error", e);
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("/getSessionOpenID")
    public Result getSessionOpenID(HttpServletRequest request, HttpServletResponse response) {
        try {
            Object openIDO = SessionUtil.getParam(request, "openID");
            String access_token = weixinService.getAccessToken();
            System.out.println("access_token:" + access_token);
            if (!ObjectUtils.isEmpty(openIDO)) {
                String openID = openIDO.toString();
                System.out.println("openID:" + openID);
                return Result.success(openID);
            } else {
                return Result.error(CodeMsg.Failed);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return Result.error(CodeMsg.Failed, ex.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/asdf")
    public Result asdf(HttpServletRequest request, HttpServletResponse response) {
        String aaa = request.getParameter("aaa");
        logger.info(aaa);
        return Result.success();
    }
}
