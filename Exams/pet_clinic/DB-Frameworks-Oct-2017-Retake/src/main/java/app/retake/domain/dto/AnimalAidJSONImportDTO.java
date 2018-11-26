package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class AnimalAidJSONImportDTO implements Serializable {
    @Expose
    //@Size(min = 3)
    private String name;
    @Expose
    //@DecimalMin("0.01")
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
