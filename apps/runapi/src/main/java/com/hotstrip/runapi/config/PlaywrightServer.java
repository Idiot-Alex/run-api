package com.hotstrip.runapi.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
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


    public static BrowserContext getBrowser() {
        if (null == browser) {
            startPlaywright();
        }
        return browser.newContext();
    }

    public static void startPlaywright() {
        log.info("start Playwright...");

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
//                .setProxy(new Proxy("http://127.0.0.1:8080"))
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
