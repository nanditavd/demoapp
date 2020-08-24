package com.nd.trade.listeners;

import com.nd.trade.services.Store;
import com.nd.trade.events.TradeCreateEvent;
import com.nd.trade.exceptions.InvalidTradeVersionException;
import com.nd.trade.exceptions.TradeNotAllowedException;
import com.nd.trade.model.Trade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TradeCreateEventListenerTest {

    @Mock
    private Store store;

    @InjectMocks
    private TradeCreateEventListener tradeCreateEventListener;

    @Test
    public void shouldListenToTradeCreateEvent() throws TradeNotAllowedException, InvalidTradeVersionException {
        Trade trade = new Trade();
        when(store.addTrade(any())).thenReturn(trade);
        tradeCreateEventListener.onApplicationEvent(new TradeCreateEvent(this, new Trade()));
        verify(store,times(1)).addTrade(trade);
    }

    @Test
    public void shouldThrowInvalidTradeException() throws TradeNotAllowedException, InvalidTradeVersionException {
        Trade trade = new Trade();
        doThrow(InvalidTradeVersionException.class).when(store).addTrade(trade);
        tradeCreateEventListener.onApplicationEvent(new TradeCreateEvent(this, new Trade()));
        assertThrows(InvalidTradeVersionException.class, ()->store.addTrade(trade));
    }


    @Test
    public void shouldThrowTradeNotAllowedException() throws TradeNotAllowedException, InvalidTradeVersionException {
        Trade trade = new Trade();
        doThrow(TradeNotAllowedException.class).when(store).addTrade(trade);
        tradeCreateEventListener.onApplicationEvent(new TradeCreateEvent(this, new Trade()));
        assertThrows(TradeNotAllowedException.class, ()->store.addTrade(trade));
    }


}