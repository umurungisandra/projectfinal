package com.example.demo.Model;

import javax.persistence.*;

/**
 * Created by sandra on 8/1/2016.
 */
@Entity
@Table(name="DriverPoint")
public class DriverPoint {
    private Integer id;
    private Driver driver;
    private int driverPoint;
    private Integer telephone;
    private boolean voided;
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "driverpoint_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Driver.class)
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Column(columnDefinition = "Integer Not NULL default 100")
    public int getDriverPoint() {
        return driverPoint;
    }

    public void setDriverPoint(int driverPoint) {
        this.driverPoint = driverPoint;
    }

    public boolean isVoided() {
        return voided;
    }

    public void setVoided(boolean voided) {
        this.voided = voided;
    }
 @Column
    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }
}
