package com.example.demo.Model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sandra on 5/17/2016.
 */
@Entity
@Table(name="USERS")
public class Users {
    private Integer id;

    private String firstName;

    private String lastName;
    private  String numberMatricule;
    private String province ;

    private String district;

    private String sector;

    private String cell;

    private String village;

    private Integer telphone;

    private String username;

    private String password;

    private Roles role;
    private Users savedBy;
    private Date savedDate;
    private boolean enabled;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "users_id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "numberMatricule", unique = true, nullable = false,length = 100)
    public String getNumberMatricule() {
        return numberMatricule;
    }

    public void setNumberMatricule(String numberMatricule) {
        this.numberMatricule = numberMatricule;
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
    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
@Column
    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    @Column(name = "telphone")
    public Integer getTelphone() {
        return telphone;
    }

    public void setTelphone(Integer telphone) {
        this.telphone = telphone;
    }

    @Column(name = "username", unique = true, nullable = false,length = 100)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,targetEntity = Users.class)
    //@Null
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

    @Column(name = "enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
