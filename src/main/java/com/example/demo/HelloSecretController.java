package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelloSecretController {
    String greeting = "Hi";

    @Value("${test}")
    String test;

    @Value("${sm://test}")
    String test2;
    @GetMapping("/")
    public String hello() throws IOException {
        System.out.println("GSM: " + test2);

        Test.getSecret();
        return test + " " + greeting + " World!";
    }
}