package com.nd.trade.publishers;

import com.nd.trade.events.TradeCreateEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TradeCreateEventPublisherTest {

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    public TradeCreateEventPublisher tradeCreateEventPublisher;


    @Test
    public void shouldPublishEvents(){
        ArgumentCaptor<TradeCreateEvent> argumentCaptor = ArgumentCaptor.forClass(TradeCreateEvent.class);
        doAnswer(invocation -> {
            TradeCreateEvent value = argumentCaptor.getValue();
            //assert if event is correct
            return null;
        }).when(publisher).publishEvent(argumentCaptor.capture());
        tradeCreateEventPublisher.publishEvent();
        verify(publisher, times(6)).publishEvent(any(TradeCreateEvent.class));
    }

}