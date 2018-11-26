package com.example.demo.dtos.views;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoryByProductsCountView {
    @Expose
    private String name;
    @Expose
    private Integer productsCount;
    @Expose
    private Double averagePrice;
    @Expose
    private Double totalRevenue;

    public CategoryByProductsCountView() {
    }

    public CategoryByProductsCountView(String name, Integer productsCount, Double averagePrice, Double totalRevenue) {
        this.name = name;
        this.productsCount = productsCount;
        this.averagePrice = (double)Math.round(averagePrice * 1000000)/1000000;;
        this.totalRevenue = (double)Math.round(totalRevenue * 100)/100;;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(Integer productsCount) {
        this.productsCount = productsCount;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
