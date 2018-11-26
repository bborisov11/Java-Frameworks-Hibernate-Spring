package org.softuni.ruk.domain.dto;

import com.google.gson.annotations.Expose;

public class ClientsJSONExportDTO {
    @Expose
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public ClientsJSONExportDTO(String fullName) {
        this.fullName = fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
