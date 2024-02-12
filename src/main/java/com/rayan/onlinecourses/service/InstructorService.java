package com.rayan.onlinecourses.service;

import java.util.List;

import com.rayan.onlinecourses.entity.Instructor;

public interface InstructorService {

    Instructor loadInstructorById(Long instructorId);

    List<Instructor> findInstructorByName(String name);

    Instructor findInstructorByEmail(String email);

    Instructor createInstructor(String firstName, String lastName, String summary, String email, String password);

    Instructor updateInstructor(Instructor instructor);

    List<Instructor> fetchInstructors();

    void removeInstructor(Long instructorId);
}
