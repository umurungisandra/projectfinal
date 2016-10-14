package com.example.demo.Model;

/**
 * Created by sandra on 8/29/2016.
 */
public class PaymentDriverContravention {
    private String contravention;
    private String name;
    private String drivingLicense;
    private String province;
    private String district;
    private String sector;

    private  int amountDue;
    private Boolean Payment;

    public PaymentDriverContravention(String contravention, String name, int amountDue, String drivingLicense,String province,String district,String sector, Boolean payment) {
        this.contravention = contravention;
        this.name = name;
        this.amountDue = amountDue;
        this.drivingLicense = drivingLicense;
        this.province=province;
        this.district=district;
        this.sector=sector;

        Payment = payment;
    }

    public PaymentDriverContravention(String name, int amount) {
    }

    public String getContravention() {
        return contravention;
    }

    public void setContravention(String contravention) {
        this.contravention = contravention;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public int getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(int amountDue) {
        this.amountDue = amountDue;
    }

    public Boolean getPayment() {
        return Payment;
    }

    public void setPayment(Boolean payment) {
        Payment = payment;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
