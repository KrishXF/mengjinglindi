package com.springmvc.service;

import com.springmvc.dto.CardDto;
import com.springmvc.pojo.Card;

import java.util.List;

public interface CardService {

       //查询未过期的卡券列表
       List<CardDto> getCardList() throws Exception;

       //新增卡券
       int insertCard(Card card)throws Exception;

       // 获取卡券详情
       CardDto getCardInfo(int id) throws Exception;

       List<Card> getCardInfoByCardIdList( List<String> cardIdList) throws Exception;
}
