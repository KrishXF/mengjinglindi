package com.springmvc.service.impl;

import com.springmvc.service.UserService;
import com.springmvc.service.WeixinService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service("UserService")
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    WeixinService weixinService;

    @Override
    public String getOpenID(String code) {
        if (StringUtils.isEmpty(code)) {
            return "";
        }
        try {
            logger.info("{}","2:"+code);
            WxMpOAuth2AccessToken accessToken = weixinService.oauth2getAccessToken(code);
            logger.info("{}", accessToken);
            if (accessToken == null || StringUtils.isEmpty(accessToken.getOpenId()))
                throw new Exception("Not invalid access token");

            String openId = accessToken.getOpenId();

            return openId;
        } catch (Exception e) {
            logger.error("getOpenId failed");
            return "failed";
        }
    }
}
