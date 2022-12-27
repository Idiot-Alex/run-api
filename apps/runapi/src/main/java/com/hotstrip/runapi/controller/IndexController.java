package com.hotstrip.runapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

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
    public String home(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            log.info("name: {}...value: {}", name, request.getHeader(name));
        }
        return "hello world";
    }

    /*@GetMapping(value = "/test")
    @ResponseBody
    public String test(@RequestParam String url) {
        log.info("url: {}", url);
        Objects.requireNonNull(url, "url cannot be null");
        BrowserContext browserContext = PlaywrightServer.getBrowser();
        Page page = browserContext.newPage();

        page.onRequest(request -> log.info(">> " + request.method() + " " + request.url()));
        page.onResponse(response -> log.info("<<" + response.status() + " " + response.url()));

        page.navigate(url);
        return page.title();
    }*/
}
