package com.nd.trade.resources;

import com.nd.trade.publishers.TradeCreateEventPublisher;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Setter
public class TradeResource {

    @Autowired
    private TradeCreateEventPublisher tradeCreateEventPublisher;

    @RequestMapping(value = "/trade", method = RequestMethod.GET)
    public ResponseEntity  createTrade() {
        tradeCreateEventPublisher.publishEvent();
        return ResponseEntity.ok(HttpStatus.OK);

    }
}
