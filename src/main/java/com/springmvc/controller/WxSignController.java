package com.springmvc.controller;

import com.alibaba.fastjson.JSONException;
import com.springmvc.cache.WxApiTicketCacheUtil;
import com.springmvc.common.CodeMsg;
import com.springmvc.common.Result;
import com.springmvc.config.WxMpConfig;
import com.springmvc.service.WeixinService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.springmvc.util.WeixinSignUtil.createSignBySha1;

@Controller
@RequestMapping(value = "/getSign",method = RequestMethod.POST)
public class WxSignController {
    @Autowired
    private WeixinService wxService;

    @Autowired
    private WxMpConfig wxMpConfig;

    @RequestMapping("/getConfig")
    @ResponseBody
    public Map<String,String> getWxConfig(HttpServletRequest request) throws JSONException {
        try {
            String requestUrl = request.getRequestURL().toString();
            String appId = wxMpConfig.getAppid();
            String jsapi_ticket = wxService.getJsapiTicket();
            String noncestr = UUID.randomUUID().toString().replace("-","");
            long time = new Date().getTime();
            SortedMap<Object, Object> map = new TreeMap<Object, Object>();
            map.put("noncestr", noncestr);
            map.put("jsapi_ticket", jsapi_ticket);
            map.put("timestamp",time+"" );
            map.put("url", requestUrl);
            String signature = createSignBySha1(map);

            Map<String,String> resultMap = new HashMap<String,String>();
            resultMap.put("appId", appId);
            resultMap.put("timestamp",time+"" );
            resultMap.put("noncestr", noncestr);
            resultMap.put("signature", signature);

            return resultMap;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/getAddCard")
    @ResponseBody
    public Result getWxAddCard(HttpServletRequest request, @RequestParam("card_id") String card_id) throws JSONException {
        try {
            String requestUrl = request.getRequestURL().toString();
            String appId = wxMpConfig.getAppid();
            WxApiTicketCacheUtil wxApiTicketCacheUtil = new WxApiTicketCacheUtil();
            String api_ticket = wxApiTicketCacheUtil.getApiTicketCache();
            String noncestr = UUID.randomUUID().toString().replace("-","");
            long time = new Date().getTime();
            SortedMap<Object, Object> map = new TreeMap<Object, Object>();
            map.put("api_ticket", api_ticket);
            map.put("timestamp",time+"" );
            map.put("card_id", card_id);
            map.put("noncestr", noncestr);
            String signature = createSignBySha1(map);

            Map<String,String> resultMap = new HashMap<String,String>();
            resultMap.put("timestamp",time+"" );
            resultMap.put("nonce_str", noncestr);
            resultMap.put("signature", signature);

            return Result.success(resultMap);
        } catch (Exception e) {
            return Result.error(CodeMsg.Failed);
        }
    }
}
