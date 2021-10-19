package com.rest.webservices.restfulrestwebservice.controller;


import com.rest.webservices.restfulrestwebservice.model.Name;
import com.rest.webservices.restfulrestwebservice.model.PersonV1;
import com.rest.webservices.restfulrestwebservice.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {


    //using uri
    @GetMapping("v1/persons")
    public PersonV1 personV1Name() {
        return new PersonV1("Mohammed");
    }

    @GetMapping("v2/persons")
    public PersonV2 personV2Name() {
        return new PersonV2(new Name("Mohammed", "Refaat"));
    }


    //versioning by header
    @GetMapping(value = "/persons/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1Name() {
        return new PersonV1("Mohammed");
    }

    @GetMapping(value = "/persons/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2Name() {
        return new PersonV2(new Name("Mohammed", "Refaat"));
    }

    //using producers version by mime type
    @GetMapping(value = "/persons/produces", produces = "application/com.learning.app-v1+json")
    public PersonV1 produceV1Name() {
        return new PersonV1("Mohammed");
    }

    @GetMapping(value = "/persons/produces", produces = "application/com.learning.app-v2+json")
    public PersonV2 produceV2Name() {
        return new PersonV2(new Name("Mohammed", "Refaat"));
    }
}
