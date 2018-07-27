package com.springmvc.service;

import com.springmvc.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {

       //新增使用人信息
       int insertUserInfo(UserInfo userInfo)throws Exception;

}
