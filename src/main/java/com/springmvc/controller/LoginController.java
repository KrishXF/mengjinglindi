package com.springmvc.controller;

import com.springmvc.pojo.Admin;
import com.springmvc.service.AdminService;
import com.springmvc.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    AdminService adminService;

    @Resource
    TestService testService;

    @RequestMapping(value = "/check.do", method = RequestMethod.POST)
    @ResponseBody
    public Map checkUser(Admin admin, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> adminInfo = adminService.checkInfo(admin);
        Object level = adminInfo.get("level");
        if (null != level && !level.equals("")) {
            HttpSession session = request.getSession();
            session.setAttribute("level", level);
            return adminInfo;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/registe.do", method = RequestMethod.POST)
    @ResponseBody
    public void registe(Admin admin, HttpServletRequest request, HttpServletResponse response) {
        adminService.insertAdmin(admin);

    }
}
