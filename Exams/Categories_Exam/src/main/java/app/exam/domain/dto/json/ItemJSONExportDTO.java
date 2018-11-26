package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ItemJSONExportDTO {
    @Expose
    @Size(min = 3, max = 30)
    private String name;
    @Expose
    @DecimalMin("0.01")
    private BigDecimal price;
    @Expose
    @DecimalMin("0.01")
    private Integer quantity;

    public ItemJSONExportDTO() {
    }

    public ItemJSONExportDTO(String name, BigDecimal price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
