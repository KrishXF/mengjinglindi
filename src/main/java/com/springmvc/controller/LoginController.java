package com.springmvc.controller;

import com.springmvc.pojo.Admin;
import com.springmvc.service.AdminService;
import com.springmvc.service.WeixinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AdminService adminService;

    @Autowired
    WeixinService weixinService;

    @RequestMapping(value = "/check.do", method = RequestMethod.POST)
    @ResponseBody
    public Map checkUser(Admin admin, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @RequestMapping(value = "/registe.do", method = RequestMethod.POST)
    @ResponseBody
    public void registe(Admin admin, HttpServletRequest request, HttpServletResponse response) {
    }
}
