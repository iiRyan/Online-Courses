package com.rayan.onlinecourses.entity;

import java.util.*;

public class Student {

    private Long studentId;
    private String firstName;
    private String lastName;
    private String level;

    // !------------ define Relationships between other Entities ------------!
    // Each Student can Enroll in many courses.
    private Set<Course> courses = new HashSet<>();

    // Each Student is a user
    private User user;

    public Student() {
    }

    public Student(String firstName, String lastName, String level, Set<Course> courses, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.level = level;
        this.courses = courses;
        this.user = user;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) && Objects.equals(firstName, student.firstName)
                && Objects.equals(lastName, student.lastName) && Objects.equals(level, student.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName, level);
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", level="
                + level + "]";
    }

}
