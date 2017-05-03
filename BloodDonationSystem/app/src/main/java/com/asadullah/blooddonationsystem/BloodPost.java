package com.asadullah.blooddonationsystem;

import java.io.Serializable;

/**
 * Created by MASS-2016 on 2/26/2017.
 */
public class BloodPost implements Serializable{
    public String bloodGroup;
    public String numOfUnits;
    public String urgency;
    public String country;
    public String state;
    public String city;
    public String hospital;
    public String relation;
    public String contact;
    public String extras;
    public String userId;

    public BloodPost(String bloodGroup, String contact, String extras, String relation, String hospital, String city, String state, String country, String urgency, String numOfUnits, String uid) {
        this.bloodGroup = bloodGroup;
        this.contact = contact;
        this.extras = extras;
        this.relation = relation;
        this.hospital = hospital;
        this.city = city;
        this.state = state;
        this.country = country;
        this.urgency = urgency;
        this.numOfUnits = numOfUnits;
        this.userId = uid;
    }
}
