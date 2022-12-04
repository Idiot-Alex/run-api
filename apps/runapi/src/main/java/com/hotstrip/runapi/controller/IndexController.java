package com.hotstrip.runapi.controller;

import com.hotstrip.runapi.config.PlaywrightServer;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * Index controller
 * @since 2022-12-04
 * @author Hotstrip
 */
@Slf4j
@Controller
public class IndexController {

    @GetMapping(value = {"/", "index", "index.html"})
    @ResponseBody
    public String home() {
        return "hello world";
    }

    @GetMapping(value = "/test")
    @ResponseBody
    public String test(@RequestParam String url) {
        log.info("url: {}", url);
        Objects.requireNonNull(url, "url cannot be null");
        Browser browser = PlaywrightServer.getBrowser();
        Page page = browser.newPage();
        page.navigate(url);
        return page.title();
    }
}
