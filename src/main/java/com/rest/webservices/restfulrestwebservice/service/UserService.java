package com.rest.webservices.restfulrestwebservice.service;


import com.rest.webservices.restfulrestwebservice.model.User;
import com.rest.webservices.restfulrestwebservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(Integer id) {
        return userRepository.findOne(id);
    }

    public User add(User user) {
        return userRepository.save(user);
    }
}
