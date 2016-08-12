package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sandra on 5/18/2016.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle {
    private Integer id;
    private String marque;
    private String carteJone;
    private String Plate;
    private String insurance;
    private String controlTechnique;

@Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCarteJone() {
        return carteJone;
    }

    public void setCarteJone(String carteJone) {
        this.carteJone = carteJone;
    }

    public String getPlate() {
        return Plate;
    }

    public void setPlate(String plate) {
        Plate = plate;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getControlTechnique() {
        return controlTechnique;
    }

    public void setControlTechnique(String controlTechnique) {
        this.controlTechnique = controlTechnique;
    }


}
