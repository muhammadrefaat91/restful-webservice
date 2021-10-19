package com.rest.webservices.restfulrestwebservice.controller;

import com.rest.webservices.restfulrestwebservice.exception.UserNotFoundException;
import com.rest.webservices.restfulrestwebservice.model.User;
import com.rest.webservices.restfulrestwebservice.service.JPAUserService;
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


//retrieving data using jpa
@RestController
public class JPAUserResource {

    @Autowired
    private JPAUserService jpaUserService;

    @Autowired
    private MessageSource messageSource;


    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return jpaUserService.findAll();
    }


    @GetMapping("/jpa/users/{id}")
    public EntityModel<Optional<User>> retrieveUser(@PathVariable("id") int id) {
        Optional<User> existedUser  =  jpaUserService.findOne(id);
        if (existedUser.isEmpty()) {
            throw new UserNotFoundException("No user provided by this id " + id);
        }

        //apply hateoas
        EntityModel<Optional<User>> userModel = EntityModel.of(existedUser);

        WebMvcLinkBuilder  links = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        userModel.add(links.withRel("all-users"));

        return userModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User savedUser = jpaUserService.add(user);
        //return current request uri
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
