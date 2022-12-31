package com.hotstrip.runapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONValidator;
import com.hotstrip.runapi.domain.model.vo.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Slf4j
@Controller
public class StompMessageController {
    @Resource
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/upload")
    @SendTo("/topic/response")
    public void upload(String data) {
        if (!JSONValidator.from(data).validate()) {
            log.error("upload data is not a json format string...");
            return;
        }
        ApiModel apiModel = JSON.parseObject(data, ApiModel.class);
        log.info("data: {}", JSON.toJSONString(apiModel));
    }
}
