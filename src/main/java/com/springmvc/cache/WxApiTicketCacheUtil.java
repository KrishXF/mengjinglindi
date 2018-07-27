package com.springmvc.cache;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springmvc.service.WeixinService;
import com.springmvc.util.HttpRequestUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class WxApiTicketCacheUtil {
    public static String api_ticket = "";

    private final static String GET_API_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=wx_card";

    @Autowired
    private WeixinService wxService;

    public void setApiTicketCache() {
        CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        cacheManagerImpl.putCache("api_ticket",api_ticket,7200*1000L);
    }

    public String getApiTicketCache() {
        CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        api_ticket = (String)cacheManagerImpl.getCacheDataByKey("api_ticket");

        if(StringUtils.isEmpty(api_ticket)){
            getApiTicket();
        }
        return api_ticket;
    }

    //获取api_ticket方法
    public void getApiTicket(){
        try {
            String accessToken = wxService.getAccessToken();
            String resultData = HttpRequestUtil.getResponse(GET_API_URL,accessToken);
            JSONObject jsonReturnData = JSON.parseObject(resultData);
            if(jsonReturnData.getString("errcode").equals("0")){
                api_ticket = jsonReturnData.getString("ticket");
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}

