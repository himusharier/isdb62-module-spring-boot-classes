package org.himusharier.controllerdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping
    @ResponseBody
    String id() {
        return "id";
    }

    @GetMapping(path = "/name", produces = "application/json")
    @ResponseBody
    String name() {
        return "sajal";
    }

    @RequestMapping(path = "/address", method = RequestMethod.GET)
    @ResponseBody
    String address() {
        return "Dhaka";
    }

    @GetMapping("/homeland")
    ResponseEntity<String> homeland() {
        ResponseEntity<String> response = new ResponseEntity<>("jashore", HttpStatus.valueOf(404));
        return response;
    }

    @GetMapping("/merul")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    String merul() {
        return "home sweet home";
    }

    @GetMapping({"/welcome/{s}","/welcome/{s}/{address}"})
    String welcome(@PathVariable(name = "s") String name, @PathVariable(name = "address",required = false) String address) {
        //String address = "merul";
        return "Welcome, " + name + ". address: " + address;
    }

    @GetMapping("/info")
    String info(@RequestParam(name = "name") String name, @RequestParam(name = "address",required = false) String address) {
        return "Welcome, " + name + ". address: " + address;
    }

    @PostMapping("/user")
    Info getInfo(@RequestBody(required = false) Info info) {
        return info;
    }

}
