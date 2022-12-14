package com.hotstrip.runapi.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StompMessageController {

    @Resource
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/change-notice")
    @SendTo("/topic/notice")
    public String greeting(String value) {
        return value;
    }


}
