package com.atalaykaan.springboot.demo.springbootexercise.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcerciseRestApplication {
    @GetMapping
    String sayHello() {
        return "Hello World";
    }
}
