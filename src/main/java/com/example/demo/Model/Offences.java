package com.example.demo.Model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by sandra on 5/18/2016.
 */
@Entity
@Table(name="OFFENCES")
public class Offences {
    private Integer id;
    @NotEmpty
    private String nameOffence;
    @NotNull
    private Punishment punishments;
    private Users savedBy;

    private Date savedDate;
    private boolean voided;
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "offences_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "name")
    public String getNameOffence() {
        return nameOffence;
    }

    public void setNameOffence(String nameOffence) {
        this.nameOffence = nameOffence;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Punishment.class)
    public Punishment getPunishments() {
        return punishments;
    }

    public void setPunishments(Punishment punishments) {
        this.punishments = punishments;
    }

    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Users.class)
    public Users getSavedBy() {
        return savedBy;
    }

    public void setSavedBy(Users savedBy) {
        this.savedBy = savedBy;
    }
    @Column(name = "savedate")
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
