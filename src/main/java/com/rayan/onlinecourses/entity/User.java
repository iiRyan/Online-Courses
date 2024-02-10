package com.rayan.onlinecourses.entity;

import java.util.*;

public class User {

    private Long userId;
    private String email;
    private String password;

    // !------------ define Relationships between other Entities ------------!
    // Each user has a set of Rolls.
    private Set<Role> roles = new HashSet<>();

    // Each User can be either student or an Instructor
    private Student student;
    private Instructor instructor;

    public User() {
    }

    public User(String email, String password, Set<Role> roles, Student student, Instructor instructor) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.student = student;
        this.instructor = instructor;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(email, user.email)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password);
    }

    public void assignRoleToUser(Role theRole) {
        this.roles.add(theRole);
        theRole.getUsers().add(this);
    }

    public void removeRoleToUser(Role theRole) {
        this.roles.remove(theRole);
        theRole.getUsers().remove(this);
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", email=" + email + ", password=" + password + "]";
    }

}
