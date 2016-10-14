package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sandra on 5/17/2016.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Driver {
    private Integer id;
    private String firstName;
    private  String lastName;
    private String sex;
    private Date dateOfBirth;
    private Date expiry;
    private String drivingLisence;
    private String category;

@Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDrivingLisence() {
        return drivingLisence;
    }

    public void setDrivingLisence(String drivingLisence) {
        this.drivingLisence = drivingLisence;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
