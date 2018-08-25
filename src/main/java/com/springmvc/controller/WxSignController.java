package com.springmvc.controller;

import com.springmvc.cache.WxApiTicketCacheUtil;
import com.springmvc.common.CodeMsg;
import com.springmvc.common.Result;
import com.springmvc.config.WxMpConfig;
import com.springmvc.service.WeixinService;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.springmvc.util.WeixinSignUtil.createSignBySha1;

@Controller
@RequestMapping(value = "/getSign")
public class WxSignController {
    @Autowired
    private WeixinService wxService;


    @Autowired
    WxMpConfig wxConfig;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/getConfig")
    @ResponseBody
    public Result getWxConfig(HttpServletRequest request, @RequestParam("url") String url) throws JSONException {
        try {
//            String appId = wxMpConfig.getAppid();
//            String jsapi_ticket = wxService.getJsapiTicket();
//            String noncestr = UUID.randomUUID().toString().replace("-","");
//            long time = new Date().getTime();
//            SortedMap<Object, Object> map = new TreeMap<Object, Object>();
//            map.put("noncestr", noncestr);
//            map.put("jsapi_ticket", jsapi_ticket);
//            map.put("timestamp",time+"" );
//            map.put("url", requestUrl);
//            String signature = createSignBySha1(map);
//
//            Map<String,String> resultMap = new HashMap<String,String>();
//            resultMap.put("appId", appId);
//            resultMap.put("timestamp",time+"" );
//            resultMap.put("noncestr", noncestr);
//            resultMap.put("signature", signature);
//
//            return resultMap;
            WxJsapiSignature signatureRet = wxService.createJsapiSignature(url);
            JSONObject res = JSONObject.fromObject(signatureRet);
            return Result.success(res);
        } catch (WxErrorException e) {
            return Result.error(CodeMsg.Failed);
        }
    }

    @RequestMapping("/getAddCard")
    @ResponseBody
    public Result getWxAddCard(HttpServletRequest request, @RequestParam("card_id") String card_id, @RequestParam("count") int count) throws JSONException {
        try {
            List cardIdList = new ArrayList();
            WxApiTicketCacheUtil wxApiTicketCacheUtil = new WxApiTicketCacheUtil();
            String accessToken = wxService.getAccessToken();
            String api_ticket = wxApiTicketCacheUtil.getApiTicketCache(accessToken);
            long time = new Date().getTime();
            String timestamp = String.valueOf(time);
            Integer times = Integer.valueOf(timestamp.substring(0, timestamp.length() - 3));
            for (int i = 1; i <= count; i++) {
                String noncestr = UUID.randomUUID().toString().replace("-", "");
                ArrayList<String> list = new ArrayList<String>();
                list.add(api_ticket);
                list.add(card_id);
                list.add(noncestr);
                list.add(times-i + "");
                String signature = createSignBySha1(list);
                list.add(signature);
                Map<String, String> resultMap = new HashMap<String, String>();
                resultMap.put("timestamp", times-i + "");
                resultMap.put("cardId", card_id);
                resultMap.put("nonce_str", noncestr);
                resultMap.put("signature", signature);
                logger.info(list.toString() + "------------------" + resultMap.toString());
                cardIdList.add(resultMap);
            }
            logger.info("-------------cardList------------------"+cardIdList.toString());
            return Result.success(cardIdList);
        } catch (Exception e) {
            return Result.error(CodeMsg.Failed);
        }
    }


    @RequestMapping("/getCardListParam")
    @ResponseBody
    public Result getCardListParam(HttpServletRequest request, @RequestParam("card_id") String card_id) throws JSONException {
/*        String accessToken = wxService.getAccessToken();
        String url = "access_token=" + accessToken + "&type=wx_card";
        String aaa = HttpGetUtil.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket", url);
        JSONObject aaaa = JSONObject.fromObject(aaa);
        String api_ticket = aaaa.get("api_ticket").toString();*/
        try {
            WxApiTicketCacheUtil wxApiTicketCacheUtil = new WxApiTicketCacheUtil();
            String api_ticket = wxApiTicketCacheUtil.getApiTicketCache(wxService.getAccessToken());
            String appid = wxConfig.getAppid();
            System.out.println("appid:" + appid);
            String timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
            Integer times = Integer.valueOf(timestamp.substring(0, timestamp.length() - 3));
            String noncestr = UUID.randomUUID().toString().replace("-", "");
            SortedMap<Object, Object> map = new TreeMap<Object, Object>();
            map.put("api_ticket", api_ticket);
            map.put("appid", appid);
            map.put("card_id", card_id);
            map.put("noncestr", noncestr);
            map.put("timestamp", times + "");

            ArrayList<String> list = new ArrayList<String>();
            list.add(api_ticket);
            list.add(appid);
            list.add(card_id);
            list.add(noncestr);
            list.add(times + "");
            String signature = createSignBySha1(list);

            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("timestamp", times + "");
            resultMap.put("nonceStr", noncestr);
            resultMap.put("signType", "SHA1");
            resultMap.put("cardId", card_id);
            resultMap.put("cardSign", signature);
            return Result.success(resultMap);
        } catch (Exception e) {
            return Result.error(CodeMsg.Failed);
        }
    }
}
