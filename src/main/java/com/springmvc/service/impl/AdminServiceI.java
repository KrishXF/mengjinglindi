package com.springmvc.service.impl;

import com.springmvc.dao.AdminMapper;
import com.springmvc.dao.CardMapper;
import com.springmvc.dao.OrderDetailMapper;
import com.springmvc.dao.UserInfoMapper;
import com.springmvc.pojo.Admin;
import com.springmvc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminService")
public class AdminServiceI implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private CardMapper cardMaper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;


    public Admin getAdmin(int id) {
        Admin a = this.adminMapper.selectByPrimaryKey(id);
        if (a != null) {
            return a;
        }
        return null;
    }

    public int insertAdmin(Admin admin) {
        return this.adminMapper.insert(admin);
    }

}
