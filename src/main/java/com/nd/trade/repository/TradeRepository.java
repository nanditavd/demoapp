package com.nd.trade.repository;

import com.nd.trade.model.Trade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends CrudRepository<Trade, Long> {

    Trade findByTradeIdAndCounterPartyAndBookId(String tradeId,String counterParty, String bookId);

    Trade findById(long id);
}