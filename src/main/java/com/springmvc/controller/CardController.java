package com.springmvc.controller;

import com.springmvc.pojo.Card;
import com.springmvc.service.CardService;
import com.springmvc.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardservice;

    @RequestMapping("/getCardList.do")
    @ResponseBody
    public List<Card> GetCardList(HttpServletRequest request, HttpServletResponse response){
           return cardservice.GetCardList();
    };

}
