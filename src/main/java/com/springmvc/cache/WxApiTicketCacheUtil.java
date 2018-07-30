package com.springmvc.cache;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springmvc.service.WeixinService;
import com.springmvc.util.HttpRequestUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

public class WxApiTicketCacheUtil {
    public static String api_ticket = "";

    private final static String GET_API_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={0}&type=wx_card";

    @Autowired
    private WeixinService wxService;

    public void setApiTicketCache() {
        CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        cacheManagerImpl.putCache("api_ticket",api_ticket,7200*1000L);
        api_ticket = "";
    }

    public String getApiTicketCache(String token) {
        CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        if(!cacheManagerImpl.isTimeOut("api_ticket")){
            api_ticket = (String)cacheManagerImpl.getCacheDataByKey("api_ticket");
            return api_ticket;
        }
        getApiTicket(token);
        return api_ticket;
    }

    //获取api_ticket方法
    public void getApiTicket(String accessToken){
        String url = MessageFormat.format(GET_API_URL,accessToken);
        String resultData = HttpRequestUtil.getResponse(url,"");
        JSONObject jsonReturnData = JSON.parseObject(resultData);
        if(jsonReturnData.getString("errcode").equals("0")){
            api_ticket = jsonReturnData.getString("ticket");
            setApiTicketCache();
        }
    }
}

