package com.springmvc.service.impl;

import com.springmvc.dao.VideomanageplatforminfoMapper;
import com.springmvc.dao.ZrvcompresstaskassigninfoMapper;
import com.springmvc.dao.ZrvcompresstaskinfoMapper;
import com.springmvc.dao.ZrvdeviceinfoMapper;
import com.springmvc.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("TestService")
public class TestServiceI implements TestService {
    @Resource
    VideomanageplatforminfoMapper videomanageplatforminfoMapper;

    @Resource
    ZrvcompresstaskassigninfoMapper zrvCompressTaskAssignInfoMapper;

    @Resource
    ZrvcompresstaskinfoMapper zrvCompressTaskInfoMapper;

    @Resource
    ZrvdeviceinfoMapper zrvDeviceInfoMapper;

    @Override
    public void test() {

    }
}
