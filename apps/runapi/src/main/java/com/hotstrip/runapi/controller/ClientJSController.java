package com.hotstrip.runapi.controller;

import com.alibaba.fastjson.JSON;
import com.hotstrip.runapi.domain.model.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Index controller
 * @since 2022-12-04
 * @author Hotstrip
 */
@Slf4j
@RestController
public class ClientJSController {
    @PostMapping(value = "/client-js/upload")
    public String upload(@RequestBody ApiModel apiModel) {
        log.info("value: {}", JSON.toJSONString(apiModel));
        return "success";
    }
}
