package com.rest.webservices.restfulrestwebservice.service;


import com.rest.webservices.restfulrestwebservice.model.User;
import com.rest.webservices.restfulrestwebservice.repository.JPAUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JPAUserService {

    @Autowired
    private JPAUserRepository jpaUserRepository;


    public List<User> findAll() {
        return jpaUserRepository.findAll();
    }

    public Optional<User> findOne(Integer id) {
        return jpaUserRepository.findById(id);
    }

    public User add(User user) {
        return jpaUserRepository.save(user);
    }

    public User edit(User user) {
        return jpaUserRepository.save(user);
    }
}
