package com.example.demo.Model;



/**
 * Created by sandra on 8/1/2016.
 */

public class PaymentDriver {
    //private String firstName;
   // private String lastName;
    private String driver;
    private int amountDue;

    private Driver d;


    public PaymentDriver(String driver,Driver d, int amountDue) {
        this.driver = driver;
        //this.firstName=firstName;
       // this.lastName=lastName;
        this.amountDue = amountDue;
        this.d=d;

    }

    public PaymentDriver() {
    }

    public Driver getD() {
        return d;
    }

    public void setD(Driver d) {
        this.d = d;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(int amountDue) {
        this.amountDue = amountDue;
    }

    //public String getFirstName() {
       // return firstName;
   // }

    //public void setFirstName(String firstName) {
       // this.firstName = firstName;
   // }

    //public String getLastName() {
        //return lastName;
   // }

    //public void setLastName(String lastName) {
       // this.lastName = lastName;
   // }


}
