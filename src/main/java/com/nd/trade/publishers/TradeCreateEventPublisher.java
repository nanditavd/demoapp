package com.nd.trade.publishers;

import com.nd.trade.TradeUtil;
import com.nd.trade.events.TradeCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TradeCreateEventPublisher  {

   @Autowired
    private  ApplicationEventPublisher publisher;



    public void publishEvent() {

        publisher.publishEvent(new TradeCreateEvent(this, TradeUtil.getTrade1()));
        publisher.publishEvent(new TradeCreateEvent(this, TradeUtil.getTrade2()));
        publisher.publishEvent(new TradeCreateEvent(this, TradeUtil.getTrade2dash()));

        publisher.publishEvent(new TradeCreateEvent(this, TradeUtil.getTradeWithMaturityDateGreaterThanToday()));
        publisher.publishEvent(new TradeCreateEvent(this, TradeUtil.getTradeWithMaturityDateLesserThanToday()));
        publisher.publishEvent(new TradeCreateEvent(this, TradeUtil.getTradeLowerThanDatabaseVersion()));

    }
}
