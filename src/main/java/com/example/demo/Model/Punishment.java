package com.example.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by sandra on 5/18/2016.
 */
@Entity
@Table(name="PUNISHEMENT")
public class Punishment {
    private Integer id;
    @NotNull
    private Operation operation;
    @NotNull
    private String description;
    private Users savedBy;
    private Date savedDate;
    private boolean voided;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "punishement_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
  //  @Column(name = "pointmax")
   // public int getPointMax() {
       // return pointMax;
    //}

   // public void setPointMax(int pointMax) {
       // this.pointMax = pointMax;
    //}
   // @Column(name = "penalties")
    //public int getPenalties() {
       // return penalties;
   // }

    //public void setPenalties(int penalties) {
        //this.penalties = penalties;
   // }
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Operation.class)
    public Operation getOperation() {
        return operation;
   }

    public void setOperation(Operation operation) {
       this.operation = operation;
    }

    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Users.class)
    public Users getSavedBy() {
        return savedBy;
    }

    public void setSavedBy(Users savedBy) {
        this.savedBy = savedBy;
    }
    @Column(name = "saveddate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }
    @Column(name = "voided")

    public boolean isVoided() {
        return voided;
    }

    public void setVoided(boolean voided) {
        this.voided = voided;
    }
}
