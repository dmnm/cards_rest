package org.example.cards.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
/*import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.containsInAnyOrder;*/
import static org.hamcrest.Matchers.is;

import org.example.cards.App;
import org.example.cards.api.CardsResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
public class CardsTest {
    @Autowired
    WebApplicationContext context;

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    MockMvc mockMvc;

    @Autowired
    CardsResource cardsResource;

    @Autowired
    ObjectMapper mapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity())
                .addFilters(springSecurityFilterChain).build();
    }

    @Test
    public void getLoansTest() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/api/v1/loans").with(httpBasic("user", "123")).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        System.err.println(result.getResponse().getContentAsString());
    }

    @Test
    public void getDepositsTest() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/api/v1/deposits").with(httpBasic("user", "123")).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        System.err.println(result.getResponse().getContentAsString());
    }

    @Test
    public void getCardsTest() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/api/v1/cards").with(httpBasic("user", "123")).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        System.err.println(result.getResponse().getContentAsString());
    }

    @Test
    public void blockCardTest() throws Exception {
        MvcResult result = mockMvc
                .perform(put("/api/v1/cards/" + 42 + "/block").with(httpBasic("user", "123"))
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        System.err.println(result.getResponse().getContentAsString());
    }

    @Test
    public void unblockCardTest() throws Exception {
        MvcResult result = mockMvc
                .perform(put("/api/v1/cards/" + 42 + "/block").with(httpBasic("user", "123"))
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        System.err.println(result.getResponse().getContentAsString());
    }

    @Test
    public void blockCard404Test() throws Exception {
        MvcResult result = mockMvc
                .perform(put("/api/v1/cards/" + -1 + "/block").with(httpBasic("user", "123"))
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print()).andReturn();

        System.err.println("Result: 404 " + result.getResponse().getContentAsString());
    }
}
