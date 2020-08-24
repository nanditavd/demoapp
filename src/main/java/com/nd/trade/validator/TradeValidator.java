package com.nd.trade.validator;

import com.nd.trade.exceptions.InvalidTradeVersionException;
import com.nd.trade.exceptions.TradeNotAllowedException;
import com.nd.trade.model.Trade;
import com.nd.trade.repository.TradeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@NoArgsConstructor
@Component
public class TradeValidator {

    @Autowired
    private TradeRepository repository;

    public Trade validate(Trade trade) throws InvalidTradeVersionException, TradeNotAllowedException {
        Trade savedTrade = repository.findByTradeIdAndCounterPartyAndBookId(trade.getTradeId(),trade.getCounterParty(),trade.getBookId());
        if(savedTrade!=null) {
            if (trade.getVersion() < savedTrade.getVersion()) {
                throw new InvalidTradeVersionException();
            }
            Date today = new Date();
            if (trade.getMaturityDate().before(today)) {
                throw new TradeNotAllowedException();
            }
            return savedTrade;
        }
       return trade;
    }
}