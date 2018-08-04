package com.springmvc.service.impl;

import com.springmvc.dao.CardMapper;
import com.springmvc.dto.CardDto;
import com.springmvc.pojo.Card;
import com.springmvc.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("CardService")
public class CardServiceImpl  implements CardService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CardMapper cardMapper;

    public List<CardDto> getCardList() throws Exception{
        Card card = new Card();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        card.setEnddate(new Date());
        List<Card>  cardlist=cardMapper.selectCardList(card);
        List<CardDto> cardDtoList=new ArrayList<CardDto>();
        for (Card item :cardlist
                ) {
            CardDto cardDto=new CardDto();
            cardDto.setEnddatestring(df.format(item.getEnddate()));
            cardDto.setId(item.getId());
            cardDto.setCardid(item.getCardid());
            cardDto.setName(item.getName());
            cardDto.setPrice(item.getPrice());
            cardDto.setType(item.getType());
            cardDto.setInventory(item.getInventory());
            cardDtoList.add(cardDto);
            }
         return cardDtoList;

    }
    public int insertCard(Card card) throws Exception{
        card.setTimestrap(new Date());
        int result=cardMapper.insert(card);

        return result;
    }
    public CardDto getCardInfo(int id) throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Card card=cardMapper.selectByPrimaryKey(id);
        CardDto cardDto=new CardDto();
        cardDto.setId(card.getId());
        cardDto.setCardid(card.getCardid());
        cardDto.setName(card.getName());
        cardDto.setPrice(card.getPrice());
        cardDto.setType(card.getType());
        cardDto.setInventory(card.getInventory());
        cardDto.setType(card.getType());
        cardDto.setPrice(card.getPrice());
        cardDto.setEnddatestring(df.format(card.getEnddate()));
        cardDto.setEnddate(card.getEnddate());
        cardDto.setStartdatestring(df.format(card.getStartdate()));
        cardDto.setStartdate(card.getStartdate());
        cardDto.setTimestrapString(df.format(card.getTimestrap()));
        cardDto.setTimestrap(card.getTimestrap());
        cardDto.setSoldnum(card.getSoldnum());
        cardDto.setImg(card.getImg());
        cardDto.setIntroduc(card.getIntroduc());
        cardDto.setRemarks(card.getRemarks());
        cardDto.setTimeremarks(card.getTimeremarks());
        cardDto.setCardstate(card.getCardstate());
        cardDto.setDoublePrice((double)card.getPrice()/100);
        return cardDto;
    }

    public  List<Card> getCardInfoByCardIdList(List<String> cardIdList) throws Exception{
        Card card = new Card();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        card.setEnddate(new Date());
        List<Card>  cardlist=cardMapper.selectCardListByAdidList(cardIdList);
//        List<CardDto> cardDtoList=new ArrayList<CardDto>();
//        for (Card item :cardlist
//                ) {
//            CardDto cardDto=new CardDto();
//            cardDto.setEnddatestring(df.format(item.getEnddate()));
//            cardDto.setId(item.getId());
//            cardDto.setCardid(item.getCardid());
//            cardDto.setName(item.getName());
//            cardDto.setPrice(item.getPrice());
//            cardDto.setType(item.getType());
//            cardDto.setInventory(item.getInventory());
//            cardDtoList.add(cardDto);
//        }
        return cardlist;
    }
}
