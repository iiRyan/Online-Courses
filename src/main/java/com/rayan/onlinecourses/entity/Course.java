package com.rayan.onlinecourses.entity;

import java.util.*;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// Define the class sas entity

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Basic
    @Column(name = "course_name", nullable = false, length = 45)
    private String courseName;

    @Basic
    @Column(name = "course_duration", nullable = false, length = 45)
    private String courseDuration;

    @Basic
    @Column(name = "course_description", nullable = false, length = 64)
    private String courseDescription;

    // !------------ define Relationships between other Entities ------------!
    // Each course Can Combine a group or a list of students.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "enrolled_in", joinColumns = { @JoinColumn(name = "course_id") }, inverseJoinColumns = {
            @JoinColumn(name = "student_id") })
    private Set<Student> students = new HashSet<>();

    // Every course can by taught a single Instructor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", referencedColumnName = "instructor_id", nullable = false)
    private Instructor instructor;

    public Course() {
    }

    public Course(String courseName, String courseDuration, String courseDescription, Instructor instructor) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseDescription = courseDescription;
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Course)) {
            return false;
        }
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId) && Objects.equals(courseName, course.courseName)
                && Objects.equals(courseDuration, course.courseDuration)
                && Objects.equals(courseDescription, course.courseDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, courseDuration, courseDescription);
    }

    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return this.courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseDescription() {
        return this.courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Set<Student> getStudents() {
        return this.students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void assignStudentToCourse(Student theStudent) {
        this.students.add(theStudent);
        theStudent.getCourses().add(this);
    }

    public void removeStudentToCourse(Student theStudent) {
        this.students.remove(theStudent);
        theStudent.getCourses().remove(this);
    }

    @Override
    public String toString() {
        return "{" +
                " courseId='" + getCourseId() + "'" +
                ", courseName='" + getCourseName() + "'" +
                ", courseDuration='" + getCourseDuration() + "'" +
                ", courseDescription='" + getCourseDescription() + "'" +
                "}";
    }

}
