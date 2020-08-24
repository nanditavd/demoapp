package com.nd.trade.services;

import com.nd.trade.exceptions.InvalidTradeVersionException;
import com.nd.trade.exceptions.TradeNotAllowedException;
import com.nd.trade.model.Trade;
import com.nd.trade.repository.TradeRepository;
import com.nd.trade.validator.TradeValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreTest {


    @Mock
    private TradeRepository repository;

    @Mock
    private TradeValidator tradeValidator;

    @InjectMocks
    private Store store;


    @Test
    public void shouldAddTradeToStoreThrowsExceptionForLowerVersion() throws TradeNotAllowedException, InvalidTradeVersionException {
        Trade trade= new Trade();
        trade.setVersion(1);
        doThrow(InvalidTradeVersionException.class).when(tradeValidator).validate(any(Trade.class));
        assertThrows(InvalidTradeVersionException.class, ()->store.addTrade(trade));
        Mockito.verify(repository,Mockito.never()).save(trade);

    }

    @Test
    public void shouldAddTradeToStoreAcceptsHigherVersion() throws InvalidTradeVersionException, TradeNotAllowedException {
        Trade trade= new Trade();
        trade.setVersion(2);
        Instant now  = Instant.now();
        Instant after = now.plus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(after));
        Trade dbTrade = trade;
        dbTrade.setId(1);
        when(tradeValidator.validate(trade)).thenReturn(dbTrade);
        when(repository.save(trade)).thenReturn(dbTrade);
        Trade savedTrade = store.addTrade(trade);
        assertThat(savedTrade.getId()).isNotNull();
        Mockito.verify(repository,Mockito.times(1)).save(trade);

    }

    @Test
    public void shouldAddTradeToStoreRejectLesserMaturityDate() throws TradeNotAllowedException, InvalidTradeVersionException {
        Trade trade= new Trade();
        trade.setVersion(3);
        Instant now  = Instant.now();
        Instant before = now.minus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(before));
        doThrow(TradeNotAllowedException.class).when(tradeValidator).validate(any(Trade.class));
        assertThrows(TradeNotAllowedException.class,()->store.addTrade(trade));

    }


    @Test
    public void shouldAddTradeToStoreAllowGreaterMaturityDate() throws TradeNotAllowedException, InvalidTradeVersionException {
        Trade trade= new Trade();
        trade.setVersion(3);
        Instant now  = Instant.now();
        Instant after = now.plus(Duration.ofDays(3));
        trade.setMaturityDate(Date.from(after));
        Trade dbTrade = trade;
        dbTrade.setId(1);
        when(tradeValidator.validate(trade)).thenReturn(dbTrade);
        when(repository.save(trade)).thenReturn(dbTrade);
        Trade savedTrade = store.addTrade(trade);
        assertThat(savedTrade.getId()).isNotNull();
        Mockito.verify(repository,Mockito.times(1)).save(trade);

    }

}