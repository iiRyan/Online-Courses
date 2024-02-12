package com.rayan.onlinecourses;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rayan.onlinecourses.dao.CourseDao;
import com.rayan.onlinecourses.dao.InstructorDao;
import com.rayan.onlinecourses.dao.RoleDao;
import com.rayan.onlinecourses.dao.StudentDao;
import com.rayan.onlinecourses.dao.UserDao;
import com.rayan.onlinecourses.utility.OperationUtility;

@SpringBootApplication
public class OnlineCoursesApplication implements CommandLineRunner {

	private UserDao userDao;
	private CourseDao courseDao;
	private InstructorDao instructorDao;
	private StudentDao studentDao;
	private RoleDao roleDao;

	public OnlineCoursesApplication(UserDao userDao, CourseDao courseDao, InstructorDao instructorDao,
			StudentDao studentDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.courseDao = courseDao;
		this.instructorDao = instructorDao;
		this.studentDao = studentDao;
		this.roleDao = roleDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(OnlineCoursesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
