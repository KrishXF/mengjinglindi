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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/wexinCard",method = RequestMethod.POST)
public class CardsActionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private  final String URL = "url";

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
    public Result createCard(HttpServletRequest resquest, @RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("basefile") MultipartFile basefile, @RequestParam("detail1") MultipartFile detail1, @RequestParam("detail2") MultipartFile detail2, @RequestParam("detail3") MultipartFile detail3, @RequestParam("cardInfo") String cardInfo, @RequestParam("baseInfo") String baseInfo,@RequestParam("localJson") String localJson, HttpServletResponse response) {
                try {
                    String logoFilePath = "";
                    logoFilePath = FileUploadUtil.uploadFile(resquest,uploadFile);
                    String  baseFilePath = FileUploadUtil.uploadFile(resquest,basefile);
                    String  detail1FilePath  = FileUploadUtil.uploadFile(resquest,detail1);
                    String  detail2FilePath  = FileUploadUtil.uploadFile(resquest,detail2);
                    String  detail3FilePath  = FileUploadUtil.uploadFile(resquest,detail3);
                    String img = detail1FilePath+","+detail2FilePath+","+detail3FilePath;
                    //调用微信方法上传logo
                    CardsCreateService cardsCre = new CardsCreateService();
                    //获取token方法
                    String accessToken= wxService.getAccessToken();
                    String logoUrl = JSON.parseObject(cardsCre.uploadLogo(logoFilePath,accessToken)).getString(URL);
                    String baseUrl = JSON.parseObject(cardsCre.uploadLogo(baseFilePath,accessToken)).getString(URL);
                    if(!StringUtils.isEmpty(logoUrl)){
                        String cardID = "";
                        cardID = cardsCre.createCardToWexin(logoUrl,baseUrl,cardInfo,accessToken);
                        //调用本地方法，新增本地卡券，保存已经获得的card_id
                        JSONObject jsonCardInfo = JSON.parseObject(localJson);
                        Card card = setCard(jsonCardInfo,cardID);
                        cardservice.insertCard(card);
                    }
                } catch (Exception ex) {
                    return Result.error(CodeMsg.Failed);
                }
                return Result.success();
    }



    //卡券核销
    @RequestMapping("/createComsume.do")
    @ResponseBody
    public Result consumeCard(HttpServletRequest resquest, @RequestParam("ecode") String ecode, HttpServletResponse response) {

            //首先获取accessToken，前文已经描写了获取方法这类就不再啰嗦了
        try {
            String accessToken = wxService.getAccessToken();
            //解密code
            String decUrl = "https://api.weixin.qq.com/card/code/decrypt?access_token=TOKEN";
            JSONObject json = new JSONObject();
            json.put("encrypt_code", ecode);
            String returnJson = HttpRequestUtil.getResponse(decUrl, json.toString());
            JSONObject jsonCardInfo = JSON.parseObject(returnJson);
            Integer retcode= jsonCardInfo.getInteger("errcode");
            if(retcode == 0){
                String code= jsonCardInfo.getString("code");

            //这串url是查询code的接口，在看文档的时候，强烈推荐先查询，所以就先试了下
            String url = "https://api.weixin.qq.com/card/code/get?access_token=" + accessToken;
            json = new JSONObject();
            json.put("code", code);
            json.put("check_consume", true);
            returnJson = HttpRequestUtil.getResponse(url, json.toString());
            jsonCardInfo = JSON.parseObject(returnJson);
            retcode= jsonCardInfo.getInteger("errcode");
            if(retcode == 0){
                //接下来是核销的接口
                String clearUrl = "https://api.weixin.qq.com/card/code/consume?access_token=" + accessToken;
                JSONObject clearJson = new JSONObject();
                clearJson.put("code", code);
                HttpRequestUtil.getResponse(clearUrl, clearJson.toString());
            }
            }
        } catch (WxErrorException e) {
            return Result.error(CodeMsg.Failed);
        }

        return Result.success();
    }



    //本地CardBean参数组装类
    public Card setCard(JSONObject jsonCardInfo,String cardID){
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