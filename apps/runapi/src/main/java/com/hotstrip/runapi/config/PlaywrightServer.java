package com.hotstrip.runapi.config;

import com.microsoft.playwright.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Playwright server
 * @since 2022-12-04
 * @author Hotstrip
 */
@Slf4j
public class PlaywrightServer {
    private static final Playwright playwright = Playwright.create();
    private static Browser browser;


    public static Browser getBrowser() {
        return browser;
    }

    public static void startPlaywright() {
        log.info("start Playwright...");

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setSlowMo(5000)
                .setChannel("chrome")
                .setHeadless(false);
        browser = playwright.chromium().launch(launchOptions);

        log.info("start Playwright success...");
    }

    public static void closePlaywrightServer() {
        log.info("close Playwright...");
        playwright.close();
    }

}
