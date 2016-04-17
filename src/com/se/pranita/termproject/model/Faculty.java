package com.se.pranita.termproject.model;

/**
 * Created by Pranita on 14/4/16.
 */
public class Faculty extends User {
    public Faculty(String netID, String password, String firstName, String lastName) {
        super(UserType.FACULTY, netID, password, firstName, lastName);
    }
}
