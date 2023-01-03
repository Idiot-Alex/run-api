package com.hotstrip.runapi.controller;

import com.hotstrip.runapi.RunapiApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Slf4j
class ApiLogControllerTest extends RunapiApplicationTests {

    @Test
    void list() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api-log/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"));
    }
}