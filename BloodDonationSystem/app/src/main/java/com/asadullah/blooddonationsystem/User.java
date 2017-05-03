package com.asadullah.blooddonationsystem;

/**
 * Created by MASS-2016 on 2/26/2017.
 */
public class User {
    public String uid;
    public String firstName;
    public String lastName;
    public String email;
    public String bloodGroup;
    public String password;


    public User(String firstName, String lastName, String email, String bloodGroup, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bloodGroup = bloodGroup;
        this.password = password;
    }


}
