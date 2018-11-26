package org.softuni.mostwanted.domain.dto;

import com.google.gson.annotations.Expose;

public class TownJSONExportDTO {
    @Expose
    private String name;
    @Expose
    private Integer racers;

    public TownJSONExportDTO() {
    }

    public TownJSONExportDTO(String name, Integer racers) {
        this.name = name;
        this.racers = racers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRacers() {
        return racers;
    }

    public void setRacers(Integer racers) {
        this.racers = racers;
    }
}
