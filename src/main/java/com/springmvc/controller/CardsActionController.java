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
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public Result createCard(HttpServletRequest resquest, @RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("basefile") MultipartFile basefile, @RequestParam("detail1") MultipartFile detail1, @RequestParam("detail2") MultipartFile detail2, @RequestParam("detail3") MultipartFile detail3, @RequestParam("cardInfo") String cardInfo, @RequestParam("baseInfo") String baseInfo, @RequestParam("localJson") String localJson, HttpServletResponse response) {
        try {
            //获取token方法
            String accessToken = wxService.getAccessToken();
            String logoFilePath = "";
            List<String> filePathList = new ArrayList<String>();
            List<String> filePathForWxList = new ArrayList<String>();
            logoFilePath = FileUploadUtil.uploadFile(resquest, uploadFile);
            String baseFilePath = FileUploadUtil.uploadFile(resquest, basefile);
            //调用微信方法上传logo
            CardsCreateService cardsCre = new CardsCreateService();
            String logoUrl = JSON.parseObject(cardsCre.uploadLogo(logoFilePath, accessToken)).getString(URL);
            String baseUrl = JSON.parseObject(cardsCre.uploadLogo(baseFilePath, accessToken)).getString(URL);

            String detail1FilePath = "";
            if(!detail1.isEmpty()){
                detail1FilePath  = FileUploadUtil.uploadFile(resquest, detail1);
                String detail1FilePathWx = JSON.parseObject(cardsCre.uploadLogo(detail1FilePath, accessToken)).getString(URL);
                filePathList.add(detail1FilePath);
                filePathForWxList.add(detail1FilePathWx);
            }

            String detail2FilePath = "";
            if(!detail2.isEmpty()){
                detail2FilePath  = FileUploadUtil.uploadFile(resquest, detail2);
                String detail2FilePathWx = JSON.parseObject(cardsCre.uploadLogo(detail2FilePath, accessToken)).getString(URL);
                filePathList.add(detail2FilePath);
                filePathForWxList.add(detail2FilePathWx);
            }

            String detail3FilePath = "";
            if(!detail3.isEmpty()){
                detail3FilePath  = FileUploadUtil.uploadFile(resquest, detail3);
                String detail3FilePathWx = JSON.parseObject(cardsCre.uploadLogo(detail3FilePath, accessToken)).getString(URL);
                filePathList.add(detail3FilePath);
                filePathForWxList.add(detail3FilePathWx);
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
    public Result cardDeciphering(HttpServletRequest resquest, @RequestParam("ecode") String ecode, HttpServletResponse response) {
        //首先获取accessToken，前文已经描写了获取方法这类就不再啰嗦了
        String decUrl = "https://api.weixin.qq.com/card/code/decrypt?access_token={0}";
        String accessToken = null;
        try {
            accessToken = wxService.getAccessToken();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        String url = MessageFormat.format(decUrl,accessToken);
        JSONObject json = new JSONObject();
        json.put("encrypt_code", ecode);
        String returnJson = HttpRequestUtil.getResponse(url, json.toString());
        JSONObject jsonCardInfo = JSON.parseObject(returnJson);
        Integer retcode = jsonCardInfo.getInteger("errcode");
        if (retcode == 0) {
            String code = jsonCardInfo.getString("code");
            return Result.success(code);
        } else {
            return Result.error(CodeMsg.Failed);
        }
    }


    //卡券核销
    @RequestMapping("/createComsume.do")
    @ResponseBody
    public Result consumeCard(HttpServletRequest resquest, @RequestParam("code") String code, HttpServletResponse response) {

        //首先获取accessToken，前文已经描写了获取方法这类就不再啰嗦了
        try {
            String accessToken = wxService.getAccessToken();
            //解密code
//            String decUrl = "https://api.weixin.qq.com/card/code/decrypt?access_token=TOKEN";
//            JSONObject json = new JSONObject();
//            json.put("encrypt_code", ecode);
//            String returnJson = HttpRequestUtil.getResponse(decUrl, json.toString());
//            JSONObject jsonCardInfo = JSON.parseObject(returnJson);
//            Integer retcode = jsonCardInfo.getInteger("errcode");
//            if (retcode == 0) {
               //String code = jsonCardInfo.getString("code");
                //这串url是查询code的接口，在看文档的时候，强烈推荐先查询，所以就先试了下
                String url = "https://api.weixin.qq.com/card/code/get?access_token=" + accessToken;
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
                        String card_id = cardJson.getString("card_id");
                        return Result.success(card_id);
                    }
                }
//            }
        } catch (WxErrorException e) {
            return Result.error(CodeMsg.Failed);
        }

        return Result.error(CodeMsg.Failed);
    }

    //本地CardBean参数组装类
    public Card setCard(JSONObject jsonCardInfo, String cardID) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Card card = new Card();
        card.setCardid(cardID);
        card.setIntroduc(jsonCardInfo.getString("Introduc"));
        card.setName(jsonCardInfo.getString("Name"));
        card.setPrice(jsonCardInfo.getInteger("Price"));
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
        return card;
    }

}