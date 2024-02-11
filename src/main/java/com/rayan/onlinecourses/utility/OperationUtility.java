package com.rayan.onlinecourses.utility;

import java.util.List;
import java.util.Optional;

import org.yaml.snakeyaml.emitter.EmitterException;

import com.rayan.onlinecourses.dao.CourseDao;
import com.rayan.onlinecourses.dao.InstructorDao;
import com.rayan.onlinecourses.dao.RoleDao;
import com.rayan.onlinecourses.dao.StudentDao;
import com.rayan.onlinecourses.dao.UserDao;
import com.rayan.onlinecourses.entity.Course;
import com.rayan.onlinecourses.entity.Instructor;
import com.rayan.onlinecourses.entity.Role;
import com.rayan.onlinecourses.entity.Student;
import com.rayan.onlinecourses.entity.User;

import jakarta.persistence.EntityNotFoundException;

public class OperationUtility {

    /* User Operations */
    public static void usersOperations(UserDao userDao) {

        createUser(userDao);
        // updateUser(userDao);
        // deleteUser(userDao);
        // fetchUser(userDao);
    }

    private static void createUser(UserDao userDao) {

        User user1 = new User("rayan@gmail.com", "pass1");
        userDao.save(user1);

        User user2 = new User("ahmed@gmail.com", "pass1");
        userDao.save(user2);

        User user3 = new User("fahad@gmail.com", "pass1");
        userDao.save(user3);

        User user4 = new User("sussan@gmail.com", "pass1");
        userDao.save(user4);

        User user5 = new User("ali@gmail.com", "pass1");
        userDao.save(user5);
    }

    private static void updateUser(UserDao userDao) {
        User user = userDao.findById(2L).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        user.setEmail("newEmail.gmail.com");

        userDao.save(user);
    }

    private static void deleteUser(UserDao userDao) {
        User user = userDao.findById(5L).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        userDao.delete(user);
    }

    private static void fetchUser(UserDao userDao) {
        userDao.findAll().forEach(user -> System.out.println(user.toString()));
    }

    /* Role Operations */
    public static void roleOperations(RoleDao roleDao) {
        // createRoles(roleDao);
        // updateRole(roleDao);
        // deleteRole(roleDao);
        // fetchRole(roleDao);
    }

    private static void createRoles(RoleDao roleDao) {
        Role admin = new Role("Admin");
        roleDao.save(admin);

        Role instructor = new Role("Instructor");
        roleDao.save(instructor);

        Role student = new Role("student");
        roleDao.save(student);
    }

    private static void updateRole(RoleDao roleDao) {
        Role role = roleDao.findById(1L).orElseThrow(() -> new EmitterException("Role Not Found"));
        role.setName("Admin");
        roleDao.save(role);
    }

    private static void deleteRole(RoleDao roleDao) {
        roleDao.deleteById(2L);
    }

    private static void fetchRole(RoleDao roleDao) {
        roleDao.findAll().forEach(role -> System.out.println(role.toString()));
    }

    /* Assign Role To a User */
    public static void assignRoleToUser(UserDao userDao, RoleDao roleDao) {
        Role role = roleDao.findByName("admin");
        if (role == null)
            throw new EntityNotFoundException("Role not Found");

        List<User> users = userDao.findAll();
        users.forEach(user -> {
            user.assignRoleToUser(role);
            userDao.save(user);
        });
    }

    /* Instructor Operations */
    public static void instructorOperations(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
        // createInstructor(userDao, instructorDao, roleDao);
        // updateInstructor(instructorDao);
        // removeInstructor(instructorDao);
        // fetchInstructors(instructorDao);
    }

    private static void createInstructor(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
        Role role = roleDao.findByName("instructor");
        if (role == null)
            throw new EntityNotFoundException("Role Not Found");

        User user1 = new User("Abdualziz@gmail.com", "pass123");
        user1.assignRoleToUser(role);
        userDao.save(user1);
        Instructor instructor1 = new Instructor("Abdualziz", "Alharbi", "Experienced Instructor", user1);
        instructorDao.save(instructor1);

        User user2 = new User("Rakan@gmail.com", "pass123");
        user2.assignRoleToUser(role);
        userDao.save(user2);
        Instructor instructor2 = new Instructor("rakan", "nasser", "Experienced Instructor", user2);
        instructorDao.save(instructor2);

    }

    private static void updateInstructor(InstructorDao instructorDao) {
        Instructor instructor = instructorDao.findById(2L)
                .orElseThrow(() -> new EntityNotFoundException("Instructor Not Found"));
        instructor.setSummary("Certified Instructor");
        instructorDao.save(instructor);
    }

    private static void removeInstructor(InstructorDao instructorDao) {
        instructorDao.deleteById(2L);
    }

    private static void fetchInstructors(InstructorDao instructorDao) {
        instructorDao.findAll().forEach(instructor -> System.out.println(instructor.toString()));
    }

    /* Student Operations */
    public static void studentOperations(UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
        // createStudent(userDao, studentDao, roleDao);
        // updateStudent(studentDao);
        // removeStudent(studentDao);
        fetchStudents(studentDao);
    }

    private static void createStudent(UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
        Role role = roleDao.findByName("student");
        if (role == null)
            throw new EntityNotFoundException("Role not found");

        User user1 = new User("test@gmail.com", "pass123");
        user1.assignRoleToUser(role);
        userDao.save(user1);

        Student student1 = new Student("khalid", "Adwani", "master", user1);
        studentDao.save(student1);

        User user2 = new User("test2@gmail.com", "pass123");
        user2.assignRoleToUser(role);
        userDao.save(user2);

        Student student2 = new Student("amani", "Adwani", "master", user2);
        studentDao.save(student2);
        user2.assignRoleToUser(role);
    }

    private static void updateStudent(StudentDao studentDao) {
        Student student = studentDao.findById(2L).orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
        student.setLastName("NewLastName");
        studentDao.save(student);
    }

    private static void removeStudent(StudentDao studentDao) {
        studentDao.deleteById(5L);
    }

    private static void fetchStudents(StudentDao studentDao) {
        studentDao.findAll().forEach(student -> System.out.println(student.toString()));
    }

    /* Courses Operations */

    public static void CoursesOperation(CourseDao courseDao, InstructorDao instructorDao, StudentDao studentDao) {
        createCourse(courseDao, instructorDao);
        updateCourse(courseDao);
        deleteCourse(courseDao);
        fetchCourse(courseDao);
        assignStudentToCourse(courseDao, studentDao);
        fetchCourseForStudent(courseDao);
    }

    private static void createCourse(CourseDao courseDao, InstructorDao instructorDao) {

        Instructor instructor = instructorDao.findById(2L)
                .orElseThrow(() -> new EntityNotFoundException("Instructor Not Found"));

        Course course = new Course("Hibernate", "5 Hours", "Introduction to Hibernate", instructor);
        courseDao.save(course);

        Course course2 = new Course("Relational Database", "3.5 Hours", "Database Fundamental", instructor);
        courseDao.save(course2);

    }

    private static void updateCourse(CourseDao courseDao) {
        Course course = courseDao.findById(2L).orElseThrow(() -> new EntityNotFoundException("Course Not Found"));
        course.setCourseDuration("newDuration 99");
        courseDao.save(course);
    }

    private static void deleteCourse(CourseDao courseDao) {
        courseDao.deleteById(2L);
    }

    private static void fetchCourse(CourseDao courseDao) {
        courseDao.findAll().forEach(course -> System.out.println(course.toString()));
    }

    private static void assignStudentToCourse(CourseDao courseDao, StudentDao studentDao) {
        Optional<Student> student1 = studentDao.findById(2L);
        Optional<Student> student2 = studentDao.findById(1L);
        Course course = courseDao.findById(2L).orElseThrow(() -> new EntityNotFoundException("Course Not found"));

        student1.ifPresent(course::assignStudentToCourse);
        student2.ifPresent(course::assignStudentToCourse);

        courseDao.save(course);
    }

    private static void fetchCourseForStudent(CourseDao courseDao) {
        courseDao.getCoursesByStudentId(1L).forEach(course -> System.out.println(course.toString()));
    }
}
