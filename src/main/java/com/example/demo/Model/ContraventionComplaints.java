package com.example.demo.Model;

/**
 * Created by sandra on 8/8/2016.
 */
public class ContraventionComplaints {
    private String contravention;
  private String name;
    private String drivingLisence;
    private String offenceName;
    private  String responsible;
    private  String complaint;

    public ContraventionComplaints(String contravention, String name, String drivingLisence, String offenceName, String responsible, String complaint) {
        this.contravention = contravention;
        this.name = name;
        this.drivingLisence = drivingLisence;
        this.offenceName = offenceName;
        this.responsible = responsible;
        this.complaint = complaint;
    }

    public ContraventionComplaints() {
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

    public String getDrivingLisence() {
        return drivingLisence;
    }

    public void setDrivingLisence(String drivingLisence) {
        this.drivingLisence = drivingLisence;
    }

    public String getOffenceName() {
        return offenceName;
    }

    public void setOffenceName(String offenceName) {
        this.offenceName = offenceName;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
