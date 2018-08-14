package com.example.demo.security.service;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.security.model.UserDto;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(long id);
}