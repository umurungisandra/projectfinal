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
    @NotEmpty
    private String nameDriver;
    @NotEmpty
    private String drivingLicense;

    private Set<Offences> offenceName = new HashSet<Offences>(0);

    @NotEmpty
    private String plateNumber ;
    @NotEmpty
    private  String descriptionOffence ;
    @NotEmpty
    private String province;
    @NotEmpty
    private String district;
    @NotEmpty
    private String sector;
    private  boolean payment;
    @NotNull
    private Users savedBy;
    //private Date datecontravention;
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
@Column
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
@Column
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
@Column
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Column
    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

   // public Date getDatecontravention() {
       // return datecontravention;
    //}

   // public void setDatecontravention(Date datecontravention) {
       // this.datecontravention = datecontravention;
    //}
}

