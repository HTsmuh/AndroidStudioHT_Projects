package com.example.saylani.blooddonation;

/**
 * Created by sayla on 26/02/2017.
 */
public class User {
    String firstname;
    String lastname;
    String emailaddress;
    String bloodgroup;
    String password;

    public User(String firstname, String lastname, String emailaddress, String bloodgroup, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailaddress = emailaddress;
        this.bloodgroup = bloodgroup;
        this.password = password;
    }
}
