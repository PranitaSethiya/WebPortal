package com.se.pranita.termproject.model;

/**
 * Created by Pranita on 14/4/16.
 */
public class Student extends User {
    public Student(String netID, String password, String firstName, String lastName) {
        super(UserType.STUDENT, netID, password, firstName, lastName);
    }
}
