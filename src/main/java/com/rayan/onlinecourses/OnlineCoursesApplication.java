package com.rayan.onlinecourses;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
import com.rayan.onlinecourses.service.CourseService;
import com.rayan.onlinecourses.service.InstructorService;
import com.rayan.onlinecourses.service.RoleService;
import com.rayan.onlinecourses.service.StudentService;
import com.rayan.onlinecourses.service.UserService;

@SpringBootApplication
public class OnlineCoursesApplication {

	public final String ADMIN = "Admin";
	public final String INSTRUCTOR = "instructor";
	public final String STUDENT = "Student";

	public static void main(String[] args) {
		SpringApplication.run(OnlineCoursesApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserService userService, RoleService roleService, StudentService studentService,
			InstructorService instructorService, CourseService courseService) {
		return args -> {

			User user1 = userService.createUser("rayan@gmail.com", "pass123");
			User user2 = userService.createUser("jojo@gmail.com", "pass123");

			Role admin = roleService.createRole("Admin");
			Role instructor = roleService.createRole("Instructor");
			Role studentAdmin = roleService.createRole("Student");

			userService.assignRoleToUser(user1.getEmail(), ADMIN);
			userService.assignRoleToUser(user1.getEmail(), INSTRUCTOR);
			userService.assignRoleToUser(user2.getEmail(), STUDENT);

			Instructor instructor1 = instructorService.createInstructor("Salah", "Awid", "Experience Instructor",
					"test@gmail.com", "password1");
			Instructor instructor2 = instructorService.createInstructor("Aljohara", "Khalid", "Beautiful Instructor",
					"aljohara@gmail.com", "password");

			Student student1 = studentService.createStudent("Faisal", "Alharbi", "Mid level", "faisal@gmail.com",
					"password");
			Student student2 = studentService.createStudent("Abuallah", "Alkhibre", "Graduate level",
					"f1f2fj@gmail.com", "password");

			Course course = courseService.createCourse("Database Principle", "2 Hours", "Database for Entry Level",
					instructor1.getInstructorId());
			Course course2 = courseService.createCourse("Spring Data JPA", "3.5 Hours", "Introduction to JPA",
					instructor2.getInstructorId());

			courseService.assignStudentToCourse(course.getCourseId(), student1.getStudentId());
			courseService.assignStudentToCourse(course2.getCourseId(), student2.getStudentId());
		};
	}
}
