package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderXMLImportDTO{
    @XmlElement
    private String customer;
    @XmlElement
    private String employee;
    @XmlElement
    private String date;
    @XmlElement
    private String type;
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<OrderItemXMLImportDTO> items;

    public OrderXMLImportDTO() {
    }

    public OrderXMLImportDTO(String customer, String employee, Date date, String type, List<OrderItemXMLImportDTO> items) {
        this.customer = customer;
        this.employee = employee;
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        this.date = df.format(date);
        this.type = type;
        this.items = items;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrderItemXMLImportDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemXMLImportDTO> items) {
        this.items = items;
    }
}
