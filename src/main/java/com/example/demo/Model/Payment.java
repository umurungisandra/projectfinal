package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sandra on 5/18/2016.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {
    private Integer id;
    private Date date;
    private  String firstName;
    private String lastName;
    private int amountDue;
    private int amountPaid;
    private String offence;
    private String drivingLicense;

    public Payment() {
    }

    public Payment(Date date, int amountDue,String firstName,String lastName,String offence, int amountPaid, String drivingLicense) {

        this.date = date;
        this.firstName=firstName;
        this.lastName=lastName;
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.drivingLicense = drivingLicense;
        this.offence=offence;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(int amountDue) {
        this.amountDue = amountDue;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
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

    public String getOffence() {
        return offence;
    }

    public void setOffence(String offence) {
        this.offence = offence;
    }
}
