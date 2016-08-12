package com.example.demo.Model;

/**
 * Created by sandra on 8/8/2016.
 */
public class ContraventionComplaints {
    Contravention contravention;
    Complaints complaints;
    int count;
    public ContraventionComplaints(Contravention contravention, Complaints complaints, int count) {
        this.contravention = contravention;
        this.complaints = complaints;
        this.count = count;
    }

    public Contravention getContravention() {
        return contravention;
    }

    public void setContravention(Contravention contravention) {
        this.contravention = contravention;
    }

    public Complaints getComplaints() {
        return complaints;
    }

    public void setComplaints(Complaints complaints) {
        this.complaints = complaints;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
