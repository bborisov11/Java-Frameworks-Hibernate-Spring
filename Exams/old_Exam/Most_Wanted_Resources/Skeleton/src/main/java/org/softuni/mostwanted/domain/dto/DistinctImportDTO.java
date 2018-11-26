package org.softuni.mostwanted.domain.dto;

import com.google.gson.annotations.Expose;

public class DistinctImportDTO {
    @Expose
    private String name;
    @Expose
    private String townName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
