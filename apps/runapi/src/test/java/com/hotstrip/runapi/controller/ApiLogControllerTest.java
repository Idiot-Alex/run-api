package com.hotstrip.runapi.controller;

import com.hotstrip.runapi.RunapiApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ApiLogControllerTest extends RunapiApplicationTests {

    @Test
    void list() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/api-log/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info("content: {}", mvcResult.getResponse().getContentAsString());
//        String requestResult = this.restTemplate
//                .getForObject(prefixUrl() + "/api-log/list", String.class);
//        log.info("result: {}", requestResult);
//        Assertions.assertThat(requestResult).contains("total");
    }
}