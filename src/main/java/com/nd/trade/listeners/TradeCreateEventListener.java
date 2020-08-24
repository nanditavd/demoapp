package com.nd.trade.listeners;

import com.nd.trade.services.Store;
import com.nd.trade.events.TradeCreateEvent;
import com.nd.trade.exceptions.InvalidTradeVersionException;
import com.nd.trade.exceptions.TradeNotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TradeCreateEventListener implements ApplicationListener<TradeCreateEvent> {

    @Autowired
    private Store store;

    @Override
    public void onApplicationEvent(TradeCreateEvent event) {
        try {
            store.addTrade(event.getTrade());
        } catch (InvalidTradeVersionException e) {
            e.printStackTrace();
        } catch (TradeNotAllowedException e) {
            e.printStackTrace();
        }
    }
}