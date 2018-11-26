package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;
import java.util.List;

public class EmployeeOrdersJSONExportDTO {
    @Expose
    @Size(min = 3, max = 30)
    private String employeeName;
    @Expose
    List<OrderJSONExportDTO> orders;

    public EmployeeOrdersJSONExportDTO() {
    }

    public EmployeeOrdersJSONExportDTO(String employeeName, List<OrderJSONExportDTO> orders) {
        this.employeeName = employeeName;
        this.orders = orders;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<OrderJSONExportDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderJSONExportDTO> orders) {
        this.orders = orders;
    }
}
