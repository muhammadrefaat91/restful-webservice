package com.rest.webservices.restfulrestwebservice.repository;

import com.rest.webservices.restfulrestwebservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAUserRepository extends JpaRepository<User, Integer> {


}
