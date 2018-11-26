package org.softuni.mostwanted.domain.dto;

import com.google.gson.annotations.Expose;

public class TownJSONImportDTO {

    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

