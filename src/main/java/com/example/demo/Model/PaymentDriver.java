package com.example.demo.Model;



/**
 * Created by sandra on 8/1/2016.
 */

public class PaymentDriver {
    private String driver;
    private int amountDue;

    public PaymentDriver(String driver, int amountDue) {
        this.driver = driver;
        this.amountDue = amountDue;
    }

    public PaymentDriver() {
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
