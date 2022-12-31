package com.hotstrip.runapi.controller;

import com.alibaba.fastjson.JSON;
import com.hotstrip.runapi.RunapiApplicationTests;
import com.hotstrip.runapi.domain.model.vo.ApiConfigModel;
import com.hotstrip.runapi.domain.model.vo.ApiModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test StompMessageController
 * @author hotstrip
 * @since 2022-12-31
 */
class StompMessageControllerTest extends RunapiApplicationTests {

    @Resource
    private StompMessageController stompMessageController;
    @Test
    void upload() throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        ApiConfigModel apiConfigModel = ApiConfigModel.builder()
                .async(true)
                .url("https://www.baidu.com")
                .method("get")
                .body("body")
                .headers(JSON.toJSONString(headers))
                .build();
        ApiModel apiModel = ApiModel.builder()
                .code("200")
                .res("..")
                .msg("success")
                .config(apiConfigModel)
                .build();
        stompMessageController.upload(JSON.toJSONString(apiModel));
    }
}