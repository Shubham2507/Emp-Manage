package com.example.demo.security.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Long> {
	
    User findByUsername(String username);
   
    
}