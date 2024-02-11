package com.rayan.onlinecourses.utility;

import java.util.List;

import org.yaml.snakeyaml.emitter.EmitterException;

import com.rayan.onlinecourses.dao.InstructorDao;
import com.rayan.onlinecourses.dao.RoleDao;
import com.rayan.onlinecourses.dao.StudentDao;
import com.rayan.onlinecourses.dao.UserDao;
import com.rayan.onlinecourses.entity.Instructor;
import com.rayan.onlinecourses.entity.Role;
import com.rayan.onlinecourses.entity.Student;
import com.rayan.onlinecourses.entity.User;

import jakarta.persistence.EntityNotFoundException;

public class OperationUtility {

    /* User Operations */
    public static void usersOperations(UserDao userDao) {

        createUser(userDao);
        updateUser(userDao);
        deleteUser(userDao);
        fetchUser(userDao);
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
        User user = userDao.findById(3L).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        userDao.delete(user);
    }

    private static void fetchUser(UserDao userDao) {
        userDao.findAll().forEach(user -> System.out.println(user.toString()));
    }

    /* Role Operations */
    public static void roleOperations(RoleDao roleDao) {
        createRoles(roleDao);
        updateRole(roleDao);
        deleteRole(roleDao);
        fetchRole(roleDao);
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
        role.setName("newAdmin");
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
        createInstructor(userDao, instructorDao, roleDao);
        updateInstructor(instructorDao);
        removeInstructor(instructorDao);
        fetchInstructors(instructorDao);
    }

    private static void createInstructor(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
        Role role = roleDao.findByName("instructorDao");
        if (role == null)
            throw new EntityNotFoundException("Role Not Found");

        User user1 = new User("Abdualziz@gmail.com", "pass123");
        userDao.save(user1);
        user1.assignRoleToUser(role);
        Instructor instructor1 = new Instructor("Abdualziz", "Alharbi", "Experienced Instructor", user1);
        instructorDao.save(instructor1);

        User user2 = new User("Rakan@gmail.com", "pass123");
        userDao.save(user2);
        user2.assignRoleToUser(role);
        Instructor instructor2 = new Instructor("rakan", "nasser", "Experienced Instructor", user2);
        instructorDao.save(instructor2);

    }

    private static void updateInstructor(InstructorDao instructorDao) {
        Instructor instructor = instructorDao.findById(2L)
                .orElseThrow(() -> new EntityNotFoundException("Instructor Not Found"));
        instructor.setSummary("Certified Instructor");
    }

    private static void removeInstructor(InstructorDao instructorDao) {
        instructorDao.deleteById(2L);
    }

    private static void fetchInstructors(InstructorDao instructorDao) {
        instructorDao.findAll().forEach(instructor -> System.out.println(instructor.toString()));
    }

    /* Student Operations */
    public static void studentOperations(UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
        createStudent(userDao, studentDao, roleDao);
        updateStudent(studentDao);
        removeStudent(studentDao);
        fetchStudents(studentDao);
    }

    private static void createStudent(UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
        Role role = roleDao.findByName("student");
        if (role == null)
            throw new EntityNotFoundException("Role not found");

        User user1 = new User("hesham@gmail.com", "pass123");
        userDao.save(user1);
        user1.assignRoleToUser(role);

        Student student1 = new Student("hesham", "Adwani", "master", user1);
        studentDao.save(student1);

        User user2 = new User("jojo@gmail.com", "pass123");
        userDao.save(user2);
        user1.assignRoleToUser(role);

        Student student2 = new Student("hesham", "Adwani", "master", user1);
        studentDao.save(student2);
        user2.assignRoleToUser(role);
    }

    private static void updateStudent(StudentDao studentDao) {
        Student student = studentDao.findById(2L).orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
        student.setLastName("NewLastName");
        studentDao.save(student);
    }

    private static void removeStudent(StudentDao studentDao) {
        studentDao.deleteById(2L);
    }

    private static void fetchStudents(StudentDao studentDao) {
        studentDao.findAll().forEach(student -> System.out.println(student.toString()));
    }

}
