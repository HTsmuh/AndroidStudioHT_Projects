package com.asadullah.blooddonationsystem;

/**
 * Created by MASS-2016 on 2/26/2017.
 */
public class Feeds {

    public String name;
    public String numOfUnitsRequired;
    public String address;
    public String urgency;
    public String contact;
    public String extraInfo;
    public int volunteers;
    public int currentRequirement;


    public Feeds(String name, String numOfUnitsRequired, String address, String urgency, String contact, String extraInfo, int volunteer, int currentRequirement){
        this.name = name;
        this.numOfUnitsRequired = numOfUnitsRequired;
        this.address = address;
        this.urgency = urgency;
        this.contact = contact;
        this.extraInfo = extraInfo;
        this.volunteers = volunteer;
        this.currentRequirement = currentRequirement;
    }
}
