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