CREATE DATABASE WebPortal;
USE WebPortal;
CREATE TABLE `users` (
  `netID` varchar(10) NOT NULL,
  `password` varchar(16) NOT NULL,
  `firstName` varchar(100) NOT NULL DEFAULT "",
  `lastName` varchar(100) NOT NULL DEFAULT "",
  `type` int(1) NOT NULL,
  `startTerm` VARCHAR(10) DEFAULT NULL,
  `program` VARCHAR(4) DEFAULT NULL,
  `department` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`netID`)
);
CREATE TABLE `resources` (
  `name` VARCHAR(100) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `info` VARCHAR(500) NOT NULL DEFAULT "",
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