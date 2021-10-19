package com.rest.webservices.restfulrestwebservice.controller;


import com.rest.webservices.restfulrestwebservice.model.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {


    @GetMapping("/hello-world/{}")
    public HelloWorldBean helloWorld() {
        return new HelloWorldBean("Hello World!");
    }
}
