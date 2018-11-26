package org.softuni.ruk.domain.dto;

import com.google.gson.annotations.Expose;

public class BranchJSONImportDTO {
    @Expose
    private String name;

    public BranchJSONImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
