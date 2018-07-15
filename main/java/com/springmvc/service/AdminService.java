package com.springmvc.service;

import com.springmvc.pojo.Admin;

import java.util.Map;


public interface AdminService {
    Admin getAdmin(int id);

    int insertAdmin(Admin admin);

    int getType(Admin admin);

    Map<String, Object> checkInfo(Admin admin);

}
