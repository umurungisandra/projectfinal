package com.example.demo.Model;

import java.util.Date;

/**
 * Created by sandra on 8/8/2016.
 */
public class ContraventionComplaints {
    private String contravention;

       private String name;
    private String drivingLicense;
    //private String offenceName;
    private  String responsible;
    private  String complaint;
    private  String decision;
    private String plateNumber;
    private Date savedDate;
//complaint.getId().toString(), complaint.getComplaint(), complaint.getDecision(), complaint.getResponsible(), complaint.getDrivingLicense().getNameDriver(), complaint.getDrivingLicense().getDrivingLicense(), complaint.getDrivingLicense().getPlateNumber(), complaint.getDrivingLicense().getOffenceName().toString(), complaint.getDrivingLicense().getSavedDate());

    public ContraventionComplaints(String contravention, String decision, String responsible, String nameDriver, String drivingLicense, String plateNumber,  String complaint, Date savedDate) {
        this.contravention = contravention;
        this.name = nameDriver;
        this.drivingLicense = drivingLicense;
      //  this.offenceName = offenceName;
        this.responsible = responsible;
        this.complaint = complaint;
        this.decision = decision;
        this.plateNumber = plateNumber;
        this.savedDate = savedDate;
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

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

//    public String getOffenceName() {
        //return offenceName;
    //}

    //public void setOffenceName(String offenceName) {
        //this.offenceName = offenceName;
    //}

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

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }


}
