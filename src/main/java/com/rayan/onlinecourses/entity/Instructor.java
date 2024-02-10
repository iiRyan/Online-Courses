package com.rayan.onlinecourses.entity;

import java.util.*;

public class Instructor {

    private Long instructorId;
    private String firstName;
    private String lastName;
    private String summary;

    // !------------ define Relationships between other Entities ------------!

    // Instructor will Teach a set of courses.
    private Set<Course> courses = new HashSet<>();

    // Each Instructor Is a user.
    private User user;

    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String summary, Set<Course> courses, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.summary = summary;
        this.courses = courses;
        this.user = user;
    }

    public Long getInstructorId() {
        return this.instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
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

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
        if (!(o instanceof Instructor)) {
            return false;
        }
        Instructor instructor = (Instructor) o;
        return Objects.equals(instructorId, instructor.instructorId) && Objects.equals(firstName, instructor.firstName)
                && Objects.equals(lastName, instructor.lastName) && Objects.equals(summary, instructor.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructorId, firstName, lastName, summary);
    }

    @Override
    public String toString() {
        return "Instructor [instructorId=" + instructorId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", summary=" + summary + "]";
    }

}
