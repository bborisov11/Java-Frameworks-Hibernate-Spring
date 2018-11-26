package com.example.demo.dtos.binding;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;

public class CategoryCreateBindingModel {
    @Expose
    @Size(min = 3,max = 15)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
