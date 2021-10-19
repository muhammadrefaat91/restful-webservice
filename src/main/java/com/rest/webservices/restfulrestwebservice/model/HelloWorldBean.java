package com.rest.webservices.restfulrestwebservice.model;

import java.io.Serializable;

public class HelloWorldBean implements Serializable {
    private String greeting;

    public HelloWorldBean(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
