package com.springmvc.service.impl;

import com.springmvc.dao.AdminMapper;
import com.springmvc.pojo.Admin;
import com.springmvc.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("AdminService")
public class AdminServiceI implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin getAdmin(int id) {
        Admin a = this.adminMapper.selectByPrimaryKey(id);

        System.out.println(a);
        if (a != null) {
            return a;
        }
        return null;
    }

    @Override
    public int insertAdmin(Admin admin) {
        return this.adminMapper.insert(admin);
    }

    @Override
    public int getType(Admin admin) {
        int type = adminMapper.getType(admin);
        return type;
    }

    /*-1为用户不存在
     * -2为用户密码不正确*/

    @Override
    public Map<String, Object> checkInfo(Admin admin) {
        String name = admin.getName();
        String password = admin.getPassword();
        Admin adminInfo = adminMapper.checkInfo(name);
        int level = -1;
        if (!"".equals(adminInfo.getPassword()) && null != adminInfo.getPassword()) {
            if (password.equals(adminInfo.getPassword())) {
                level = adminInfo.getLevel();
            } else {
                /*密码不正确*/
                level = -2;
            }
        }
        Map<String, Object> adminMap = new HashMap();
        adminMap.put("level", level);
        return adminMap;
    }


}
