package com.springmvc.controller;

import com.springmvc.common.CodeMsg;
import com.springmvc.common.Result;
import com.springmvc.dto.CardDto;
import com.springmvc.pojo.Card;
import com.springmvc.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/card")
public class CardController {

    private final static Logger logger =LoggerFactory.getLogger(CardController.class);
    @Autowired
    CardService cardservice;

    @RequestMapping("/getCardList.do")
    @ResponseBody
    public Result getCardList(HttpServletRequest request, HttpServletResponse response){
        try {
             return Result.success(cardservice.getCardList());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return Result.error(CodeMsg.Failed,ex.getMessage());
        }
    }
    @RequestMapping("/getCardInfo.do")
    @ResponseBody
    public Result getCardInfo(HttpServletRequest request, HttpServletResponse response){
        try {
            int id=Integer.parseInt(URLDecoder.decode(request.getParameter("id"), "utf-8") ) ;
            CardDto cardDto=cardservice.getCardInfo(id);
            return Result.success(cardDto);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  Result.error(CodeMsg.Failed,ex.getMessage());
        }
    }


    @RequestMapping("/insertCard.do")
    @ResponseBody
    public Result insertCard(Card card,HttpServletRequest request, HttpServletResponse response){
        try {
            int resultdata=cardservice.insertCard(card);
            if ( resultdata==1 ){
                return Result.success();
            }else {
                return  Result.error(CodeMsg.Failed);
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  Result.error(CodeMsg.Failed,ex.getMessage());
        }
    }

}
