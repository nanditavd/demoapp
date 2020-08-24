package com.nd.trade.demo;

import com.nd.trade.listeners.TradeCreateEventListener;
import com.nd.trade.publishers.TradeCreateEventPublisher;
import com.nd.trade.resources.TradeResource;
import com.nd.trade.services.Store;
import com.nd.trade.validator.TradeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {


    @Bean
    public TradeValidator tradeValidator(){
        return new TradeValidator();
    }


    @Bean
    public TradeCreateEventPublisher tradeCreateEventPublisher(){
        return new TradeCreateEventPublisher();
    }

    @Bean
    public TradeCreateEventListener tradeCreateEventListener(){
        return new TradeCreateEventListener();
    }


    @Bean
    public Store store(){
        return new Store();
    }

    @Bean
    public TradeResource tradeResource(){
        return new TradeResource();
    }


}
