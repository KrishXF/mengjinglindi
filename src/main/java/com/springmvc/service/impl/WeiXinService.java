
package com.springmvc.service.impl;

import com.springmvc.config.WxMpConfig;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class WeiXinService extends WxMpServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WxMpConfig wxConfig;

    @PostConstruct
    public void init() {
        final WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(this.wxConfig.getAppid());// 设置微信公众号的appid
        config.setSecret(this.wxConfig.getAppsecret());// 设置微信公众号的app corpSecret
        config.setToken(this.wxConfig.getToken());// 设置微信公众号的token
        config.setAesKey(this.wxConfig.getAesKey());// 设置消息加解密密钥
        super.setWxMpConfigStorage(config);
    }
}
