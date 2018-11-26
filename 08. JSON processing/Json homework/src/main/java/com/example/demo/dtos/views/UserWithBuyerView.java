package com.example.demo.dtos.views;

import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

public class UserWithBuyerView {
        @Expose
        private String firstName;
        @Expose
        private String lastName;
        @Expose
        private Set<SoldProductView> soldProducts;

    public UserWithBuyerView() {
        this.soldProducts = new HashSet<>();
    }

    public UserWithBuyerView(String firstName, String lastName, Set<SoldProductView> soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.soldProducts = soldProducts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<SoldProductView> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<SoldProductView> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
