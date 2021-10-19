package com.rest.webservices.restfulrestwebservice.repository;

import com.rest.webservices.restfulrestwebservice.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository {

    static List<User> userList = new ArrayList<>();
    private static int userCount = 4;

    static {

        userList.add(new User(1, "Mohammed", new Date()));
        userList.add(new User(2, "Refaat", new Date()));
        userList.add(new User(3, "Ali", new Date()));
        userList.add(new User(4, "Hassan", new Date()));
    }

    public List<User> findAll() {
        return userList;
    }

    public Optional<User> findOne(Integer id) {
        return userList.stream().filter((user -> user.getId().equals(id))).findAny();
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }

        userList.add(user);
        return user;
    }




}
