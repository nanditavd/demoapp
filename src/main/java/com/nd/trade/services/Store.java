package com.nd.trade.services;

import com.nd.trade.exceptions.InvalidTradeVersionException;
import com.nd.trade.exceptions.TradeNotAllowedException;
import com.nd.trade.model.Trade;
import com.nd.trade.repository.TradeRepository;
import com.nd.trade.validator.TradeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Store {

    @Autowired
    private TradeValidator tradeValidator;

    @Autowired
    private TradeRepository tradeRepository;

    public Trade addTrade(Trade trade) throws InvalidTradeVersionException, TradeNotAllowedException {
        Trade t = tradeValidator.validate(trade);
        tradeRepository.save(t);
        return trade;

    }
}
