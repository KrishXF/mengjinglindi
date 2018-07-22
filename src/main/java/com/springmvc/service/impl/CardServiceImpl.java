package com.springmvc.service.impl;

import com.springmvc.dao.CardMapper;
import com.springmvc.pojo.Card;
import com.springmvc.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CardService")
public class CardServiceImpl  implements CardService {

    @Autowired
    private CardMapper cardMapper;

    public List<Card> GetCardList() {
      try {
            Card card = new Card();
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String date1=df.format(new Date());
//            Date date = df.parse(date1);
//            card.setEnddate(date);
              List<Card>  cardlist=cardMapper.selectCarList(card);
            return cardlist;
       } catch (Exception ex) {
           return null;
       }

    }
}
