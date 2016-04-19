package com.se.pranita.termproject.model;

/**
 * Created by Pranita on 14/4/16.
 */
public abstract class User {

    private String netID;
    private String password;
    private String firstName;
    private String lastName;
    private UserType type;

    public User(UserType type){
        this.type = type;
    }

    public User(UserType type, String netID, String password, String firstName, String lastName) {
        this.type = type;
        this.netID = netID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public enum UserType {
        STUDENT(0), FACULTY(1), STAFF(2);

        int value;
        UserType(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }
    }
}
