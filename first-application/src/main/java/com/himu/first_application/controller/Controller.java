package com.himu.first_application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/bye")
    public String bye() {
        return "good bye";
    }

    @GetMapping("/square")
    public int square(@RequestParam int num) {
        return num * num;
    }

    @GetMapping("/personalInfo")
    public String personalInfo(@RequestParam String me, @RequestParam String father) {
        String output = "hello, i'm " + me + ". father's name: " + father;
        return output;
    }

}
