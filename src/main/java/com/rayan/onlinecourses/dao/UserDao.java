package com.rayan.onlinecourses.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rayan.onlinecourses.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
