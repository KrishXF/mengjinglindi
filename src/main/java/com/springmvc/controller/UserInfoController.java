package com.springmvc.controller;

import com.springmvc.pojo.UserInfo;
import com.springmvc.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

@Controller
@RequestMapping("/order")
public class UserInfoController {

    private final static Logger logger =LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    UserInfoService userInfoservice;

    @RequestMapping("/insertUserInfo.do")
    @ResponseBody
    public String insertUserInfo(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response){
        return null;
    }







}
