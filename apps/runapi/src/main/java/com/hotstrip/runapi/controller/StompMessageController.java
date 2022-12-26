package com.hotstrip.runapi.controller;

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
    public String greeting(String value) {
        log.info("value: {}", value);
        return value;
    }
}
