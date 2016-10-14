package com.example.demo.Model;



/**
 * Created by sandra on 8/1/2016.
 */

public class PaymentDriver {
    private String driver;
    private int amountDue;

    private Driver d;

    public PaymentDriver(String driver,Driver d, int amountDue) {
        this.driver = driver;
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
}
