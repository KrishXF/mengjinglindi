package com.springmvc.service.impl;

import com.springmvc.dao.CardMapper;
import com.springmvc.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CardServiceI implements CardService {

    @Autowired
    private CardMapper cardMapper;

    @Override
    public List<Map> GetCarcList() {
        List<Map> cardList = cardMapper.getCardList();
        return cardList;
    }


}
