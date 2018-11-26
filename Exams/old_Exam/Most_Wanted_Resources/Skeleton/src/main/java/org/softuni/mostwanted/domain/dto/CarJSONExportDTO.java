package org.softuni.mostwanted.domain.dto;

import com.google.gson.annotations.Expose;

public class CarJSONExportDTO {

    @Expose
    private String brand;
    @Expose
    private String model;
    @Expose
    private Integer yearOfProduction;
    @Expose
    private String text;

    public CarJSONExportDTO(String brand, String model, Integer yearOfProduction) {
        this.brand = brand;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
    }

    public CarJSONExportDTO(String text) {
        this.text = text;
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

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
