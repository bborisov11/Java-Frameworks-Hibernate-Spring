package org.softuni.mostwanted.domain.dto;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Set;

public class RacerJSONExportDTO {
    @Expose
    private String name;
    @Expose
    private Integer age;
    @Expose
    private List<CarJSONExportDTO> cars;

    public RacerJSONExportDTO(String name, Integer age,List<CarJSONExportDTO> cars) {
        this.name = name;
        this.age = age;
        this.cars = cars;
    }

    public RacerJSONExportDTO(String name, List<CarJSONExportDTO> cars) {
        this.name = name;
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CarJSONExportDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarJSONExportDTO> cars) {
        this.cars = cars;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
