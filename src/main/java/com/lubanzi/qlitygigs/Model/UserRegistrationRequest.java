package com.lubanzi.qlitygigs.Model;

import org.springframework.data.annotation.Id;

public class UserRegistrationRequest {

    private String id;  //ID Number of user.

    private String uName;
    private String uSurname;

    private String uEmail;

    private String uPassword;
    private String uContactNumber;


    private String uType; //Either Requester or Gigger

    private String uDOB;
    private String uCompany; //Can be null if user is a Requester
    private String uCountry;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuSurname() {
        return uSurname;
    }

    public void setuSurname(String uSurname) {
        this.uSurname = uSurname;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuContactNumber() {
        return uContactNumber;
    }

    public void setuContactNumber(String uContactNumber) {
        this.uContactNumber = uContactNumber;
    }

    public String getuType() {
        return uType;
    }

    public void setuType(String uType) {
        this.uType = uType;
    }

    public String getuDOB() {
        return uDOB;
    }

    public void setuDOB(String uDOB) {
        this.uDOB = uDOB;
    }

    public String getuCompany() {
        return uCompany;
    }

    public void setuCompany(String uCompany) {
        this.uCompany = uCompany;
    }

    public String getuCountry() {
        return uCountry;
    }

    public void setuCountry(String uCountry) {
        this.uCountry = uCountry;
    }
}
