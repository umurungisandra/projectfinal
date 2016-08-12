package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sandra on 5/17/2016.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Police {
    private  Integer id;
    private String firstName;
    private  String lastName;
    private String rank;
    private  String stationPolice;
    private String numberMatricule;

@Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getStationPolice() {
        return stationPolice;
    }

    public void setStationPolice(String stationPolice) {
        this.stationPolice = stationPolice;
    }

    public String getNumberMatricule() {
        return numberMatricule;
    }

    public void setNumberMatricule(String numberMatricule) {
        this.numberMatricule = numberMatricule;
    }
}
