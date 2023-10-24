package com.hc.tcp_backend.clientmodels;

import java.sql.Date;

public class Profile {
    private int patient_id;
    private String name;

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    private Date dob;
    private String gender;
    private String phone_number;
    private String location;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    private String pin;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private boolean isActive;

    public Profile(int patient_id, String name, Date dob, String gender, String phone_number, String location, boolean isActive, String pin) {
        this.patient_id = patient_id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phone_number = phone_number;
        this.location = location;
        this.isActive = isActive;
        this.pin = pin;
    }
}
