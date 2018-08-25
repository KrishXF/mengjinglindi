package com.springmvc.controller;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.springmvc.common.CodeMsg;
import com.springmvc.common.Result;
import com.springmvc.config.MyWXPayConfig;
import com.springmvc.config.WxMpConfig;
import com.springmvc.pojo.PayInfo;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.*;

import static com.github.wxpay.sdk.WXPayConstants.SignType.MD5;

@Controller
@RequestMapping("/pay")
public class WXPayController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    WxMpConfig wxConfig;

    @RequestMapping("/createOrder")
    @ResponseBody
    public Result createOrder(HttpServletRequest request, HttpServletResponse response, PayInfo payInfo) {
        logger.info("进入支付接口");
        logger.info("==============================");
        logger.info("getOpenID:" + payInfo.getOpenID());
        logger.info("==============================");
        logger.info("getOrderID:" + payInfo.getOrderID());
        logger.info("==============================");
        logger.info("getTotal_fee:" + payInfo.getTotal_fee());
        logger.info("==============================");
        logger.info("getPayAppId:" + wxConfig.getPayAppId());
        logger.info("==============================");
        logger.info("getMchId:" + wxConfig.getMchId());
        logger.info("==============================");
        logger.info("getKey：" + wxConfig.getKey());
        logger.info("==============================");
        try {
            MyWXPayConfig config = MyWXPayConfig.getInstance(wxConfig);
            WXPay wxpay = new WXPay(config);
            String noncestr = UUID.randomUUID().toString().replace("-", "");
            wxConfig.setNonceStr(noncestr);
            logger.info("==============================");
            logger.info("getNonceStr:" + wxConfig.getNonceStr());
            SortedMap<String, String> data = new TreeMap<String, String>();
            data.put("openid", payInfo.getOpenID());
            data.put("appid", wxConfig.getPayAppId());
            data.put("mch_id", wxConfig.getMchId());
            data.put("nonce_str", wxConfig.getNonceStr());
            data.put("body", "service");
            data.put("out_trade_no", payInfo.getOrderID());
            data.put("device_info", "WEB");
            data.put("fee_type", "CNY");
            data.put("total_fee", payInfo.getTotal_fee());
            data.put("spbill_create_ip", "118.25.10.216");
            data.put("notify_url", "http://demo.steam-steam.cn/pay/callBack.do");
            data.put("trade_type", "JSAPI");  // 此处指定为扫码支付
            data.put("sign", WXPayUtil.generateSignature(data, wxConfig.getKey(), MD5));
            Map<String, String> resp = wxpay.unifiedOrder(data);
            if (resp.get("return_msg") != null) {
                wxConfig.setWxPackage("prepay_id=" + resp.get("prepay_id"));
                wxConfig.setTimeStamp(System.currentTimeMillis() / 1000 + "");
                SortedMap<String, String> paySignMap = new TreeMap<String, String>();
                paySignMap.put("appId", wxConfig.getPayAppId());
                paySignMap.put("timeStamp", wxConfig.getTimeStamp());
                paySignMap.put("nonceStr", wxConfig.getNonceStr());
                paySignMap.put("package", wxConfig.getWxPackage());
                paySignMap.put("signType", wxConfig.getSignType());
                wxConfig.setPaySign(WXPayUtil.generateSignature(paySignMap, wxConfig.getKey(), MD5));
            }
            return Result.success(JSONObject.fromObject(wxConfig));

        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return Result.error(CodeMsg.Failed, e.getMessage());
        }
    }


    @RequestMapping("/callBack")
    @ResponseBody
    public Result callBack(HttpServletRequest request, HttpServletResponse response) {
        try {
            BufferedReader reader = request.getReader();
            String line = "";
            String xmlString = null;
            StringBuffer inputString = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                inputString.append(line);
            }
            xmlString = inputString.toString();
            System.out.println("=====================================\n" + xmlString);
            request.getReader().close();
            // parser xml to map by weixinUtil;
            Map<String, String> map = new HashMap<String, String>();
            map = WXPayUtil.xmlToMap(xmlString);
            return Result.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.Failed);
        }


    }

}
