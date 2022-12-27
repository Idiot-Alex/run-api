/*
package com.hotstrip.runapi.config;

import com.microsoft.playwright.BrowserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlaywrightNetworkRecorder {

    public void test() {
        BrowserContext browserContext = PlaywrightServer.getBrowser();
        browserContext.pages().forEach(page -> {
            log.info("page title: {}", page.title());
            page.onRequest(request -> log.info(">> " + request.method() + " " + request.url()));
            page.onResponse(response -> log.info("<<" + response.status() + " " + response.url()));
        });
    }
}
*/
