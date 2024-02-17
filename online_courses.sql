-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema online-courses
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema online-courses
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `online-courses` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `online-courses` ;

-- -----------------------------------------------------
-- Table `online-courses`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online-courses`.`users` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `UK_6dotkott2kjsp8vw4d0m25fb7` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `online-courses`.`instructor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online-courses`.`instructor` (
  `instructor_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `summary` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`instructor_id`),
  UNIQUE INDEX `UK_cr0g7gh88hv7sfdx9kqbrbiyw` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKl05wgmungp55i9sr39da79agy`
    FOREIGN KEY (`user_id`)
    REFERENCES `online-courses`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `online-courses`.`courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online-courses`.`courses` (
  `course_id` BIGINT NOT NULL AUTO_INCREMENT,
  `instructor_id` BIGINT NOT NULL,
  `course_duration` VARCHAR(45) NOT NULL,
  `course_name` VARCHAR(45) NOT NULL,
  `course_description` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`course_id`),
  INDEX `FK79arv901b5gkyp2240wcm3l76` (`instructor_id` ASC) VISIBLE,
  CONSTRAINT `FK79arv901b5gkyp2240wcm3l76`
    FOREIGN KEY (`instructor_id`)
    REFERENCES `online-courses`.`instructor` (`instructor_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `online-courses`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online-courses`.`students` (
  `student_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `level` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE INDEX `UK_g4fwvutq09fjdlb4bb0byp7t` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKdt1cjx5ve5bdabmuuf3ibrwaq`
    FOREIGN KEY (`user_id`)
    REFERENCES `online-courses`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `online-courses`.`enrolled_in`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online-courses`.`enrolled_in` (
  `course_id` BIGINT NOT NULL,
  `student_id` BIGINT NOT NULL,
  PRIMARY KEY (`course_id`, `student_id`),
  INDEX `FKxohjeipv3lx28npivuh6yl6y` (`student_id` ASC) VISIBLE,
  CONSTRAINT `FKkdxv4hftgaw77iw8nhj57g50y`
    FOREIGN KEY (`course_id`)
    REFERENCES `online-courses`.`courses` (`course_id`),
  CONSTRAINT `FKxohjeipv3lx28npivuh6yl6y`
    FOREIGN KEY (`student_id`)
    REFERENCES `online-courses`.`students` (`student_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `online-courses`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online-courses`.`roles` (
  `role_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `UK_ofx66keruapi6vyqpv6f2or37` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `online-courses`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online-courses`.`user_role` (
  `role_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`role_id`, `user_id`),
  INDEX `FKj345gk1bovqvfame88rcx7yyx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx`
    FOREIGN KEY (`user_id`)
    REFERENCES `online-courses`.`users` (`user_id`),
  CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q`
    FOREIGN KEY (`role_id`)
    REFERENCES `online-courses`.`roles` (`role_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
