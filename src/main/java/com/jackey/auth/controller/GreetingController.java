package com.jackey.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form")
public class GreetingController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello, Welcome...";
    }
}
