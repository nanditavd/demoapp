package com.nd.trade.resources;

import com.nd.trade.demo.ApplicationConfig;
import com.nd.trade.demo.DemoTradeApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@SpringBootTest(classes = DemoTradeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TradeResourceIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void shouldSaveTrade() {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<HttpStatus> response = restTemplate.exchange(
                createURLWithPort("/api/v1/trade"),
                HttpMethod.GET, entity, HttpStatus.class);


       assertThat(response.getStatusCode().is2xxSuccessful());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}