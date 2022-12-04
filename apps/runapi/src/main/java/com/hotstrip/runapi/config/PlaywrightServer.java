package com.hotstrip.runapi.config;

import com.microsoft.playwright.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Playwright server
 * @since 2022-12-04
 * @author hotstrip
 */
public class PlaywrightServer {
    private static Playwright playwright = Playwright.create();
    private static Browser browser;


    public static Browser getBrowser() {
        return browser;
    }

    public static void startPlaywright() {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(false);
        browser = playwright.chromium().launch(launchOptions);

        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://www.baidu.com");
    }

    public static void closePlaywrightServer() {
        playwright.close();
    }

}
