package com.qws.link.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiwenshuai
 * @note
 * @since 19-1-5 15:58 by jdk 1.8
 */
@RestController
public class DemoConrtoller {


    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
