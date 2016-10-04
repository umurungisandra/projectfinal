package com.example.demo.Model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by sandra on 5/26/2016.
 */
@Entity
@Table(name ="COMPLAINTS")
public class Complaints {
    private Integer id;
    @NotEmpty
    private  String complaint;
    @NotNull
    private Contravention drivingLicense;
    @NotEmpty
    private String decision;
    @NotNull
    private Date decisionDate;
    @NotNull
    private Date complaintDate;
    @NotEmpty
    private String responsible;
    private Users savedBy;
    private Date savedDate;
    private boolean voided;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaints_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="complaint")
    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Contravention.class)
    public Contravention getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(Contravention drivingLicense) {
        this.drivingLicense = drivingLicense;
    }
    @Column(name="decision")
    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
    @Column(name="decisiondate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        this.decisionDate = decisionDate;
    }
    @Column(name="complaintdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(Date complaintDate) {
        this.complaintDate = complaintDate;
    }
    @Column(name="responsible")
    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Users.class)
    public Users getSavedBy() {
        return savedBy;
    }

    public void setSavedBy(Users savedBy) {
        this.savedBy = savedBy;
    }
    @Column(name="voided")
    public boolean isVoided() {
        return voided;
    }

    public void setVoided(boolean voided) {
        this.voided = voided;
    }
    @Column(name="saveddate")

    public Date getSavedDate() {
        return savedDate;
    }
      //enabled=true;
    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }
}
