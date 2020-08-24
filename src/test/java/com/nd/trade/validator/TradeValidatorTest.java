package com.nd.trade.validator;

import com.nd.trade.exceptions.InvalidTradeVersionException;
import com.nd.trade.exceptions.TradeNotAllowedException;
import com.nd.trade.model.Trade;
import com.nd.trade.repository.TradeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TradeValidatorTest {

    @Mock
    private TradeRepository repository;

    @InjectMocks
    private TradeValidator tradeValidator;


    @Test
    public void shouldAllowTradeWithHigherVersion() {
        Trade trade= getTrade();
        trade.setVersion(3);
        Instant now  = Instant.now();
        Instant after = now.plus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(after));
        Trade mockTrade = new Trade();
        mockTrade.setVersion(2);
        when(repository.findByTradeIdAndCounterPartyAndBookId(anyString(),anyString(),anyString())).thenReturn(mockTrade);
        Assertions.assertDoesNotThrow(()->tradeValidator.validate(trade));
    }

    @Test
    public void shouldRejectTradeWithLowerVersion() {
        Trade trade= getTrade();
        trade.setVersion(1);
        Instant now  = Instant.now();
        Instant after = now.plus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(after));
        Trade mockTrade = new Trade();
        mockTrade.setVersion(2);
        when(repository.findByTradeIdAndCounterPartyAndBookId(anyString(),anyString(),anyString())).thenReturn(mockTrade);
        assertThrows(InvalidTradeVersionException.class, ()->tradeValidator.validate(trade));
    }


    @Test
    public void shouldAllowTradeWithHigherMaturityDate() {
        Trade trade= getTrade();
        trade.setVersion(3);
        Instant now  = Instant.now();
        Instant after = now.plus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(after));
        Trade mockTrade = new Trade();
        mockTrade.setVersion(2);
        when(repository.findByTradeIdAndCounterPartyAndBookId(anyString(),anyString(),anyString())).thenReturn(mockTrade);
        Assertions.assertDoesNotThrow(()->tradeValidator.validate(trade));
    }

    @Test
    public void shouldRejectTradeWithLowerMaturityDate() {
        Trade trade= getTrade();
        trade.setVersion(1);
        Instant now  = Instant.now();
        Instant after = now.minus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(after));
        Trade mockTrade = new Trade();
        mockTrade.setVersion(1);
        when(repository.findByTradeIdAndCounterPartyAndBookId(anyString(),anyString(),anyString())).thenReturn(mockTrade);
        assertThrows(TradeNotAllowedException.class, ()->tradeValidator.validate(trade));
    }

    @Test
    public void shouldReturnTrade() throws TradeNotAllowedException, InvalidTradeVersionException {
        Trade trade= getTrade();
        trade.setVersion(3);
        Instant now  = Instant.now();
        Instant after = now.plus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(after));
        Trade mockTrade = new Trade();
        mockTrade.setVersion(2);
        when(repository.findByTradeIdAndCounterPartyAndBookId(anyString(),anyString(),anyString())).thenReturn(null);
        Trade returnValue= tradeValidator.validate(trade);
        assertThat(returnValue).isEqualTo(trade);
    }


    public Trade getTrade(){
        Trade trade= new Trade();
        trade.setCounterParty("CP-1");
        trade.setBookId("BP-1");
        trade.setTradeId("T1");
        trade.setStatus("Y");
        trade.setCreatedDate(new Date());
        return trade;
    }
}