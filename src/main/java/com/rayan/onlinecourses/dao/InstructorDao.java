package com.rayan.onlinecourses.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rayan.onlinecourses.entity.Instructor;

public interface InstructorDao extends JpaRepository<Instructor, Long> {

    @Query(value = "SELECT i FROM Instructor as i WHERE i.firstName like %:name% or i.lastName like %:name%")
    List<Instructor> findInstructorByName(@Param("name") String name);

    @Query(value = "SELECT i FROM Instructor as i WHERE i.user.email =:email")
    Instructor findInstructorByEmail(@Param("email") String email);
}
