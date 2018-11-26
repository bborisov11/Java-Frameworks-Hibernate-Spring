package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "most-popular-item")
@XmlAccessorType(XmlAccessType.FIELD)
public class MostPopularItemDTO {
    @XmlElement
    private String name;
    @XmlElement(name = "total-made")
    private BigDecimal totalPrice;
    @XmlElement(name = "times-sold")
    private Integer timesSold;

    public MostPopularItemDTO() {
    }

    public MostPopularItemDTO(String name, BigDecimal totalPrice, Integer timesSold) {
        this.name = name;
        this.totalPrice = totalPrice;
        this.timesSold = timesSold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTimesSold() {
        return timesSold;
    }

    public void setTimesSold(Integer timesSold) {
        this.timesSold = timesSold;
    }
}
