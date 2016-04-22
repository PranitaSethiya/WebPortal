CREATE DATABASE WebPortalDB;
USE WebPortalDB;
CREATE TABLE `users` (
  `netID` varchar(10) NOT NULL,
  `password` varchar(16) NOT NULL,
  `firstName` varchar(100) NOT NULL DEFAULT '',
  `lastName` varchar(100) NOT NULL DEFAULT '',
  `type` int(1) NOT NULL,
  `startTerm` VARCHAR(10) DEFAULT NULL,
  `startYear` INT(4) DEFAULT NULL,
  `program` VARCHAR(4) DEFAULT NULL,
  `department` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`netID`)
);
CREATE TABLE `resources` (
  `name` VARCHAR(100) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `info` VARCHAR(500) NOT NULL DEFAULT '',
  PRIMARY KEY (`name`)
);
CREATE TABLE `reservations` (
  `name` VARCHAR(100) NOT NULL,
  `netID` VARCHAR(10) NOT NULL,
  `slot_date` DATE NOT NULL,
  `slot_time_range` VARCHAR(5),
  FOREIGN KEY (`name`) REFERENCES `resources`(`name`),
  FOREIGN KEY (`netID`) REFERENCES `users`(`netID`),
  PRIMARY KEY (`name`,`netID`,`slot_date`,`slot_time_range`)
);
CREATE TABLE `courses` (
  `number` VARCHAR(10) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `department` VARCHAR(100) NOT NULL,
  `course_syllabus` TEXT NOT NULL DEFAULT '',
  `ins_office_hour` VARCHAR(100) DEFAULT NULL,
  `ins_office` VARCHAR(100) DEFAULT NULL,
  `ta_name` VARCHAR(100) DEFAULT NULL,
  `ta_office_hour` VARCHAR(100) DEFAULT NULL,
  `ta_office` VARCHAR(100) DEFAULT NULL,
  `ta_email` VARCHAR(100) DEFAULT NULL,
  `term` VARCHAR(10) NOT NULL,
  `year` INT(4) NOT NULL,
  PRIMARY KEY (`number`, `term`, `year`)
);
CREATE TABLE `course_user` (
  `netID` varchar(10) NOT NULL,
  `number` VARCHAR(10) NOT NULL,
  `term` VARCHAR(10) NOT NULL,
  `year` INT(4) NOT NULL,
  FOREIGN KEY (`netID`) REFERENCES `users`(`netID`),
  FOREIGN KEY (`number`, `term`, `year`) REFERENCES `courses`(`number`, `term`, `year`)
);