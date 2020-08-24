package com.nd.trade.resources;

import com.nd.trade.demo.DemoTradeApplication;
import com.nd.trade.publishers.TradeCreateEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DemoTradeApplication.class)
@AutoConfigureMockMvc
public class TradeResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TradeCreateEventPublisher tradeCreateEventPublisher;


    @BeforeEach
    public void setup() {
        TradeResource tradeResource = new TradeResource();
        tradeResource.setTradeCreateEventPublisher(tradeCreateEventPublisher);
        this.mockMvc = MockMvcBuilders.standaloneSetup(tradeResource).build();
    }

    @Test
    void whenPublishEventThenReturns200() throws Exception {
        doNothing().when(tradeCreateEventPublisher).publishEvent();
        this.mockMvc.perform(get("/api/v1/trade"))
                .andExpect(status().isOk());
    }

}