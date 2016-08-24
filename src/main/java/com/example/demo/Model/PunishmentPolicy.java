package com.example.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by sandra on 7/2/2016.
 */
@Entity
@Table
public class PunishmentPolicy {
    private Integer id;
    private int setMark;
    private int setPenalties;
    private Users savedBy;
    private Date savedDate;
    private boolean voided;
@Id
@GeneratedValue(strategy =  GenerationType.IDENTITY)
@Column(name = "operation_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
@Column
    public int getSetMark() {
        return setMark;
    }

    public void setSetMark(int setMark) {
        this.setMark = setMark;
    }
@Column
    public int getSetPenalties() {
        return setPenalties;
    }

    public void setSetPenalties(int setPenalties) {
        this.setPenalties = setPenalties;
    }

//@Column
    //public String getPayment() {
        //return payment;
   // }

   // public void setPayment(String payment) {
       //this.payment = payment;
    //}
   @ManyToOne(cascade = CascadeType.ALL,targetEntity = Users.class)
    public Users getSavedBy() {
        return savedBy;
    }

    public void setSavedBy(Users savedBy) {
        this.savedBy = savedBy;
    }
    @Column(name = "savedDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }
@Column
    public boolean isVoided() {
        return voided;
    }

    public void setVoided(boolean voided) {
        this.voided = voided;
    }


}
