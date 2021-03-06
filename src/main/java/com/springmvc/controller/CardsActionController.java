package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springmvc.common.CodeMsg;
import com.springmvc.common.Result;
import com.springmvc.pojo.Card;
import com.springmvc.service.CardService;
import com.springmvc.service.CardsCreateService;
import com.springmvc.service.OrderService;
import com.springmvc.service.WeixinService;
import com.springmvc.util.FileUploadUtil;
import com.springmvc.util.HttpRequestUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/wexinCard")
public class CardsActionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String URL = "url";

    @Autowired
    private WeixinService wxService;

    @Autowired
    CardService cardservice;

    @Autowired
    OrderService orderservice;

    //图片保存到本地，返回url 调用上传logo方法
    //获得卡卷具体信息，先组成json，调用创建卡券，获得微信返回的信息，调用本地保存卡券方法。
    @RequestMapping("/createCard.do")
    @ResponseBody
    public Result createCard(HttpServletRequest resquest, @RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("basefile") MultipartFile basefile,@RequestParam("locallogo") MultipartFile locallogo, @RequestParam("detail1") MultipartFile detail1, @RequestParam("detail2") MultipartFile detail2, @RequestParam("detail3") MultipartFile detail3, @RequestParam("cardInfo") String cardInfo, @RequestParam("baseInfo") String baseInfo, @RequestParam("localJson") String localJson, HttpServletResponse response) {
        try {
            //获取token方法
            String accessToken = wxService.getAccessToken();
            String logoFilePath = "";
            String localLogoFilePath = "";
            List<String> filePathForWxList = new ArrayList<String>();
            List<String> filePathList = new ArrayList<String>();
            logoFilePath = FileUploadUtil.uploadFile(resquest, uploadFile);
            localLogoFilePath = FileUploadUtil.uploadFile(resquest, locallogo);
            filePathList.add(localLogoFilePath);
            String baseFilePath = FileUploadUtil.uploadFile(resquest, basefile);
            //调用微信方法上传logo
            CardsCreateService cardsCre = new CardsCreateService();
            String logoUrl = JSON.parseObject(cardsCre.uploadLogo(logoFilePath, accessToken)).getString(URL);
            String baseUrl = JSON.parseObject(cardsCre.uploadLogo(baseFilePath, accessToken)).getString(URL);

            String detail1FilePath = "";
            if(!detail1.isEmpty()){
                detail1FilePath  = FileUploadUtil.uploadFile(resquest, detail1);
                String detail1FilePathWx = JSON.parseObject(cardsCre.uploadLogo(detail1FilePath, accessToken)).getString(URL);
                filePathForWxList.add(detail1FilePathWx);
            }

            String detail2FilePath = "";
            if(!detail2.isEmpty()){
                detail2FilePath  = FileUploadUtil.uploadFile(resquest, detail2);
                String detail2FilePathWx = JSON.parseObject(cardsCre.uploadLogo(detail2FilePath, accessToken)).getString(URL);
                filePathForWxList.add(detail2FilePathWx);
            }

            String detail3FilePath = "";
            if(!detail3.isEmpty()){
                detail3FilePath  = FileUploadUtil.uploadFile(resquest, detail3);
                filePathList.add(detail3FilePath);
            }

            if (!StringUtils.isEmpty(logoUrl)) {
                String cardID = "";
                cardID = cardsCre.createCardToWexin(logoUrl, baseUrl, cardInfo, accessToken,filePathForWxList);
                //调用本地方法，新增本地卡券，保存已经获得的card_id
                JSONObject jsonCardInfo = JSON.parseObject(localJson);
                Card card = setCard(jsonCardInfo, cardID);
                String img = "";
                for (String filename : filePathList) {
                    String name = filename.substring(filename.lastIndexOf("\\")+1);
                    img += ",http://demo.steam-steam.cn/img/" + name;
                }
                card.setImg(img.substring(1));
                cardservice.insertCard(card);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return Result.error(CodeMsg.Failed);
        }
        return Result.success();
    }

    //卡券解密--wrj
    @RequestMapping("/cardDeciphering")
    @ResponseBody
    public Result cardDeciphering(HttpServletRequest resquest, @RequestParam("ecodelist[]") List<String> ecodeList, HttpServletResponse response) {
        //首先获取accessToken，前文已经描写了获取方法这类就不再啰嗦了
        String decUrl = "https://api.weixin.qq.com/card/code/decrypt?access_token={0}";
        String accessToken = null;
        try {
            accessToken = wxService.getAccessToken();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        String url = MessageFormat.format(decUrl, accessToken);
        Map<String, String> successMap = new HashMap<String, String>();
        Map<String, String> failMap = new HashMap<String, String>();
        //List<String> ecodeList = java.util.Arrays.asList(ecodeStr.split(","));
        for (String ecode : ecodeList) {
            JSONObject json = new JSONObject();
            json.put("encrypt_code", ecode);
            String returnJson = HttpRequestUtil.getResponse(url, json.toString());
            JSONObject jsonCardInfo = JSON.parseObject(returnJson);
            Integer retcode = jsonCardInfo.getInteger("errcode");
            if (retcode == 0) {
                String code = jsonCardInfo.getString("code");
                successMap.put(ecode,code);
            } else {
                failMap.put(ecode,ecode);
            }
        }
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("success",successMap);
        retMap.put("error",failMap);
        System.out.println(retMap);
        return Result.success(retMap);
    }

    //卡券核销
    @RequestMapping("/createComsume.do")
    @ResponseBody
    public Result consumeCard(HttpServletRequest resquest, @RequestParam("codelist[]") List<String> codeList, HttpServletResponse response) {
        List<String> successList = new ArrayList<String>();
        List<String> failList = new ArrayList<String>();
        //首先获取accessToken，前文已经描写了获取方法这类就不再啰嗦了
        try {
            String accessToken = wxService.getAccessToken();
            String url = "https://api.weixin.qq.com/card/code/get?access_token=" + accessToken;
            for (String code : codeList) {
                JSONObject json = new JSONObject();
                json.put("code", code);
                json.put("check_consume", true);
                String returnJson = HttpRequestUtil.getResponse(url, json.toString());
                JSONObject jsonCardInfo = JSON.parseObject(returnJson);
                Integer retcode = jsonCardInfo.getInteger("errcode");
                if (retcode == 0) {
                    //接下来是核销的接口
                    String clearUrl = "https://api.weixin.qq.com/card/code/consume?access_token=" + accessToken;
                    JSONObject clearJson = new JSONObject();
                    clearJson.put("code", code);
                    returnJson = HttpRequestUtil.getResponse(clearUrl, clearJson.toString());
                    jsonCardInfo = JSON.parseObject(returnJson);
                    retcode = jsonCardInfo.getInteger("errcode");
                    if (retcode == 0) {
                        JSONObject cardJson = jsonCardInfo.getJSONObject("card");
                        successList.add(code);
                    }else{
                        failList.add(code);
                    }
                }else{
                    failList.add(code);
                }
            }
        } catch (WxErrorException e) {
            return Result.error(CodeMsg.Failed);
    }
    JSONObject resulstJson = new JSONObject();
        resulstJson.put("success",successList);
        resulstJson.put("error",failList);
        System.out.println(resulstJson);
        return Result.success(resulstJson);
    }


    //卡券解密并调用新增订单--wrj
    @RequestMapping("/cardDecrypt")
    @ResponseBody
    public Result cardDecryptAndAdd(HttpServletRequest resquest, @RequestParam("ecodelist[]") List<String> ecodeList,String orderGroupId, HttpServletResponse response) {
        //首先获取accessToken，前文已经描写了获取方法这类就不再啰嗦了
        String decUrl = "https://api.weixin.qq.com/card/code/decrypt?access_token={0}";
        String accessToken = null;
        try {
            accessToken = wxService.getAccessToken();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        String url = MessageFormat.format(decUrl, accessToken);
        Map<String, String> successMap = new HashMap<String, String>();
        Map<String, String> failMap = new HashMap<String, String>();
        List<String> codeList = new ArrayList<String>();
        //List<String> ecodeList = java.util.Arrays.asList(ecodeStr.split(","));
        for (String ecode : ecodeList) {
            JSONObject json = new JSONObject();
            json.put("encrypt_code", ecode);
            String returnJson = HttpRequestUtil.getResponse(url, json.toString());
            JSONObject jsonCardInfo = JSON.parseObject(returnJson);
            Integer retcode = jsonCardInfo.getInteger("errcode");
            if (retcode == 0) {
                String code = jsonCardInfo.getString("code");
                codeList.add(code);
                successMap.put(ecode,code);
            } else {
                failMap.put(ecode,ecode);
            }
        }
        try {
            orderservice.insertOrderWXCode(codeList,orderGroupId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("success",successMap);
        retMap.put("error",failMap);
        System.out.println(retMap);
        return Result.success(retMap);
    }

    //本地CardBean参数组装类
    public Card setCard(JSONObject jsonCardInfo, String cardID) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Card card = new Card();
        card.setCardid(cardID);
        card.setIntroduc(jsonCardInfo.getString("Introduc"));
        card.setName(jsonCardInfo.getString("Name"));
        Double price = Double.valueOf(jsonCardInfo.get("Price").toString())*100;
        card.setPrice(price.intValue());
        card.setInventory(jsonCardInfo.getInteger("Inventory"));
        card.setSoldnum(0);
        card.setCardstate(0);
        card.setRemarks(jsonCardInfo.getString("Remarks"));
        try {
            card.setStartdate(format.parse(jsonCardInfo.getString("StartDate")));
            card.setEnddate(format.parse(jsonCardInfo.getString("EndDate")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        card.setTimestrap(new Date());
        card.setTimeremarks(jsonCardInfo.getString("TimeRemarks"));
        card.setType(jsonCardInfo.getInteger("Type"));
        return card;
    }

}