package com.hotstrip.runapi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class PlaywrightTask {

    @Resource
    private PlaywrightNetworkRecorder playwrightNetworkRecorder;

    @Scheduled(fixedRate = 1000L)
    public void test() {
        log.info("scheduled test .....");
        playwrightNetworkRecorder.test();
    }
}
