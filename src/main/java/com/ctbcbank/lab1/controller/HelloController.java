package com.ctbcbank.lab1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(String name) {
        return "Hello World1 --- " + name ;
    }
}
