package com.springmvc.controller;

import com.springmvc.pojo.Admin;
import com.springmvc.pojo.Card;
import com.springmvc.service.AdminService;
import com.springmvc.service.CardService;
import com.springmvc.service.WeixinService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdminService adminService;

    @Autowired
    CardService cardService;

    @Autowired
    WeixinService weixinService;


    @RequestMapping(value = "/check.do", method = RequestMethod.POST)
    @ResponseBody
    public Map checkUser(Admin admin, HttpServletRequest request, HttpServletResponse response) {
        Card card = new Card();
        return null;
    }


    @RequestMapping(value = "/registe.do", method = RequestMethod.POST)
    @ResponseBody
    public void registe(HttpServletRequest request, HttpServletResponse response) {
        try {
            String accessToken = weixinService.getAccessToken();
            System.out.println(accessToken);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }


    }
}
