package org.softuni.ruk.domain.dto;

import com.google.gson.annotations.Expose;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class EmployeesJSONExportDTO {
  //  @Transient
    private Integer id;
    @Expose
    private String full_name;
    @Expose
    private BigDecimal salary;
    @Expose
    private String started_on;
    @Expose
    private Set<String> clients;


    public EmployeesJSONExportDTO(String full_name, BigDecimal salary, String started_on, Set<String> clients) {
        this.full_name = full_name;
        this.salary = salary;
        this.started_on = started_on;
        this.clients = new HashSet<>();
        this.clients = clients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getStarted_on() {
        return started_on;
    }

    public void setStarted_on(String started_on) {
        this.started_on = started_on;
    }

    public Set<String> getClients() {
        return clients;
    }

    public void setClients(Set<String> clients) {
        this.clients = clients;
    }
}
