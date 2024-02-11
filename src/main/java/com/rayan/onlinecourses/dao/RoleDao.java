package com.rayan.onlinecourses.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rayan.onlinecourses.entity.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
