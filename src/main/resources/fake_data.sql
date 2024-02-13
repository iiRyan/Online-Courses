-- Insert data into users table
INSERT INTO `online-courses`.`users` (`email`, `password`) VALUES
('john.doe@example.com', 'johnpassword'),
('jane.smith@example.com', 'janepassword'),
('bob.johnson@example.com', 'bobpassword'),
('alice.williams@example.com', 'alicepassword');

-- Insert data into instructor table
INSERT INTO `online-courses`.`instructor` (`user_id`, `first_name`, `last_name`, `summary`) VALUES
(1, 'Rayan', 'Salah', 'Experienced instructor in programming'),
(2, 'Jane', 'Smith', 'Passionate about teaching mathematics'),
(3, 'Bob', 'Johnson', 'Expert in data science and machine learning'),
(4, 'Alice', 'Williams', 'Experienced in web development');

-- Insert data into courses table
INSERT INTO `online-courses`.`courses` (`instructor_id`, `course_duration`, `course_name`, `course_description`) VALUES
(1, '2 months', 'Programming 101', 'Learn the basics of programming'),
(2, '3 months', 'Mathematics Fundamentals', 'Build a strong foundation in mathematics'),
(3, '1 month', 'Data Science Essentials', 'Introduction to data science and machine learning'),
(4, '2.5 months', 'Web Development Bootcamp', 'Hands-on training in web development');

-- Insert data into students table
INSERT INTO `online-courses`.`students` (`user_id`, `first_name`, `last_name`, `level`) VALUES
(1, 'Faisal', 'Salah', 'Beginner'),
(2, 'Taeq', 'Mohammed', 'Intermediate'),
(3, 'Talal', 'Ahmad', 'Advanced'),
(4, 'Talal', 'Anas', 'Intermediate');

-- Insert data into enrolled_in table
INSERT INTO `online-courses`.`enrolled_in` (`course_id`, `student_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- Insert data into roles table
INSERT INTO `online-courses`.`roles` (`name`) VALUES
('Admin'),
('Instructor'),
('Student');

-- Insert data into user_role table
INSERT INTO `online-courses`.`user_role` (`role_id`, `user_id`) VALUES
(1, 1),
(2, 2),
(3, 3);
INSERT INTO `online-courses`.`users` (`email`, `password`) VALUES ('john.doe@example.com', 'johnpassword'), ('jane.smith@example.com', 'janepassword'), ('bob.johnson@example.com', 'bobpassword'), ('alice.williams@example.com', 'alicepassword')
