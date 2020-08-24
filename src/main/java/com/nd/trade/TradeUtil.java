package com.nd.trade;

import com.nd.trade.model.Trade;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class TradeUtil {

    public static Trade getTrade1() {
        Trade trade = new Trade();
        trade.setVersion(1);
        Instant now = Instant.now();
        Instant after = now.plus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(after));
        trade.setTradeId("T1");
        trade.setCounterParty("CP-1");
        trade.setBookId("B-1");
        trade.setCreatedDate(Date.from(now));
        trade.setStatus("Y");
        return trade;
    }

    public static Trade getTrade2() {
        Trade trade = new Trade();
        trade.setVersion(1);
        Instant now = Instant.now();
        Instant after = now.plus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(after));
        trade.setTradeId("T2");
        trade.setCounterParty("CP-1");
        trade.setBookId("B-1");
        trade.setCreatedDate(Date.from(now));
        trade.setStatus("Y");
        return trade;
    }

    public static Trade getTrade2dash() {
        Trade trade = new Trade();
        trade.setVersion(2);
        Instant now = Instant.now();
        Instant after = now.plus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(after));
        trade.setTradeId("T2");
        trade.setCounterParty("CP-2");
        trade.setBookId("BP-1");
        trade.setCreatedDate(Date.from(now));
        trade.setStatus("Y");
        return trade;
    }

    public static Trade getTradeLowerThanDatabaseVersion() {
        Trade trade = new Trade();
        trade.setVersion(3);
        Instant now = Instant.now();
        Instant after = now.plus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(after));
        trade.setCounterParty("CP-1");
        trade.setBookId("BP-1");
        trade.setTradeId("T4");
        trade.setCreatedDate(Date.from(now));
        trade.setStatus("Y");
        return trade;
    }

    public static Trade getTradeWithMaturityDateLesserThanToday() {
        Trade trade = new Trade();
        trade.setVersion(3);
        Instant now = Instant.now();
        Instant before = now.minus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(before));
        trade.setCounterParty("CP-1");
        trade.setBookId("BP-1");
        trade.setTradeId("T4");
        trade.setCreatedDate(Date.from(now));
        trade.setStatus("Y");
        return trade;
    }


    public static Trade getTradeWithMaturityDateGreaterThanToday(){
        Trade trade= new Trade();
        trade.setVersion(6);
        Instant now  = Instant.now();
        Instant after = now.plus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(after));
        trade.setCounterParty("CP-2");
        trade.setBookId("BP-2");
        trade.setTradeId("T4");
        trade.setCreatedDate(Date.from(now));
        trade.setStatus("Y");
        return trade;
    }

}
