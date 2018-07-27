package com.springmvc.service.impl;

import com.springmvc.dao.UserInfoMapper;
import com.springmvc.pojo.UserInfo;
import com.springmvc.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoMapper userInfoMapper;



    public int insertUserInfo(UserInfo userInfo) throws Exception{
        int result=userInfoMapper.insertSelective(userInfo);
        return result;
    }

}
