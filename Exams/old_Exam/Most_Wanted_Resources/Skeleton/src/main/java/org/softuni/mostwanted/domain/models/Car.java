package org.softuni.mostwanted.domain.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    private BigDecimal price;
    @Column(nullable = false)
    private Integer yearOfProduction;
    private Double maxSpeed;
    private Double zeroToSixty;
    @ManyToOne
    private Racer racer;
    @OneToMany(mappedBy = "car")
    private Set<RaceEntry> raceEntities;
    @Transient
    private String text;

    public Car() {
    }

    public Car(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getZeroToSixty() {
        return zeroToSixty;
    }

    public void setZeroToSixty(Double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public Set<RaceEntry> getRaceEntities() {
        return raceEntities;
    }

    public void setRaceEntities(Set<RaceEntry> raceEntities) {
        this.raceEntities = raceEntities;
    }

    public String getText() {
        return "brand" + brand + " " + "model" + model + " " + "yearOfProduction" + yearOfProduction;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "brand" + brand + " " + "model" + model + " " + "yearOfProduction" + yearOfProduction;
    }
}
