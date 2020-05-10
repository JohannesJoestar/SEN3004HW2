package com.homework.project.model;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "weatherdata")
public class Weather {
    
    //// Properties
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "city")
    private String city;

    @Past
    @DateTimeFormat(pattern =" dd-MM-yyyy")
    @Column(name = "date_measured")
    private LocalDate dateMeasured;

    @Column(name = "temperature_min")
    private double minTemperature;

    @Column(name = "temperature_max")
    private double maxTemperature;

    //// Methods
    // Access modifiers
    public long getID() {
        return ID;
    }
    public void setID(long ID) {
        this.ID = ID;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDateMeasured() {
        return dateMeasured;
    }
    public void setDateMeasured(LocalDate dateMeasured) {
        this.dateMeasured = dateMeasured;
    }

    public double getMinTemperature() {
        return minTemperature;
    }
    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }
    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}