package com.rest.webservices.restfulrestwebservice.controller;

import com.rest.webservices.restfulrestwebservice.exception.UserNotFoundException;
import com.rest.webservices.restfulrestwebservice.model.User;
import com.rest.webservices.restfulrestwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;


    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }


    @GetMapping("/users/{id}")
    public EntityModel<Optional<User>> retrieveUser(@PathVariable("id") int id) {
        Optional<User> existedUser  =  userService.findOne(id);
        if (existedUser.isEmpty()) {
            throw new UserNotFoundException("No user provided by this id " + id);
        }

        //apply hateoas
        EntityModel<Optional<User>> userModel = EntityModel.of(existedUser);

        WebMvcLinkBuilder  links = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        userModel.add(links.withRel("all-users"));

        return userModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User savedUser = userService.add(user);
        //return current request uri
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    //internationalization
    @GetMapping("/hello-internationalized")
    public String helloWorldInternationalization(
    //        @RequestHeader(name = "Accept-Language", required = false) Locale  locale
    )  {
        return messageSource.getMessage("good.morning", null, "Default messagge", LocaleContextHolder.getLocale());
    }
}
