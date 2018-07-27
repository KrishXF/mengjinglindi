package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.springmvc.common.CodeMsg;
import com.springmvc.common.Result;
import com.springmvc.service.CardsCreateService;
import com.springmvc.service.WeixinService;
import com.springmvc.util.FileUploadUtil;
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

@Controller
@RequestMapping(value = "/wexinCard",method = RequestMethod.POST)
public class CardsCreationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private  final String URL = "url";

    @Autowired
    private WeixinService wxService;

    //图片保存到本地，返回url 调用上传logo方法
    //获得卡卷具体信息，先组成json，调用创建卡券，获得微信返回的信息，调用本地保存卡券方法。
    @RequestMapping("/createCard.do")
    @ResponseBody
    public Result createCard(HttpServletRequest resquest, @RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("basefile") MultipartFile basefile, @RequestParam("detail1") MultipartFile detail1, @RequestParam("detail2") MultipartFile detail2, @RequestParam("detail3") MultipartFile detail3, @RequestParam("cardInfo") String cardInfo, @RequestParam("baseInfo") String baseInfo, HttpServletResponse response) {
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
                        

                    }
                } catch (Exception ex) {
                    return Result.error(CodeMsg.Failed);
                }
                return Result.success();
    }
}