package com.nd.trade.events;

import com.nd.trade.model.Trade;
import org.springframework.context.ApplicationEvent;

public class TradeCreateEvent extends ApplicationEvent {

    private Trade trade;
    public TradeCreateEvent(Object source, Trade trade) {
        super(source);
        this.trade = trade;
    }

    public Trade getTrade() {
        return trade;
    }
}
