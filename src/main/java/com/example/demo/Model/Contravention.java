package com.example.demo.Model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sandra on 5/18/2016.
 */
@Entity
@Table(name="CONTRAVENTION")
public class Contravention {
    private Integer id;

    private String nameDriver;

    private String drivingLicense;

    private Set<Offences> offenceName = new HashSet<Offences>(0);
    @NotEmpty
    private String policeOfficer;
    @NotEmpty
    private String plateNumber ;
    @NotEmpty
    private  String descriptionOffence ;
    private int amount;
    private  boolean payment;
    @NotNull
    private Users savedBy;
    private Date savedDate;

    private boolean voided;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contravention_id")

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "name")

    public String getNameDriver() {
        return nameDriver;
    }

    public void setNameDriver(String nameDriver) {
        this.nameDriver = nameDriver;
    }

    @Column
    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    @Column
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Offences.class)
    public Set<Offences> getOffenceName() {
        return offenceName;
    }

    public void setOffenceName(Set<Offences> offenceName) {
        this.offenceName = offenceName;
    }
    @Column
    public String getPoliceOfficer() {
        return policeOfficer;
    }

    public void setPoliceOfficer(String policeOfficer) {
        this.policeOfficer = policeOfficer;
    }

    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Users.class)
    public Users getSavedBy() {
        return savedBy;
    }

    public void setSavedBy(Users savedBy) {
        this.savedBy = savedBy;
    }


    @Column(name="saveddate")
    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }
    @Column(name="voided")

    public boolean isVoided() {
        return voided;
    }

    public void setVoided(boolean voided) {
        this.voided = voided;
    }

    @Column(name="descriptionoffence")
    public String getDescriptionOffence() {
        return descriptionOffence;
    }

    public void setDescriptionOffence(String descriptionOffence) {
        this.descriptionOffence = descriptionOffence;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Column
    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }
}

