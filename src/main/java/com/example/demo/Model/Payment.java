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
    private int amountDue;
    private int amountPaid;
    private String numberBankslip;
    private String drivingLicense;


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

    public int  getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }


    public String getNumberBankslip() {
        return numberBankslip;
    }

    public void setNumberBankslip(String numberBankslip) {
        this.numberBankslip = numberBankslip;
    }


    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }
}
