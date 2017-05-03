package com.example.hp.logintesting;

/**
 * Created by HP on 4/13/2017.
 */
public class User {

    String firstname;
    String lastname;
    String emailaddress;
    String password;
    String gender;
    String contact;

    public User(String firstname, String lastname, String emailaddress, String password, String gender,String contact) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailaddress = emailaddress;
        this.password = password;
        this.gender = gender;
        this.contact=contact;
    }
}

