package com.hotstrip.runapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
