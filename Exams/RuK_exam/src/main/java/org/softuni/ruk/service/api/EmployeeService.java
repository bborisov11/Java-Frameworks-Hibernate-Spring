package org.softuni.ruk.service.api;

import org.softuni.ruk.domain.dto.EmployeeJSONImportDTO;
import org.softuni.ruk.domain.dto.EmployeesJSONExportDTO;

import java.util.List;

public interface EmployeeService {
    void create(EmployeeJSONImportDTO dto);

    void generateClients();

    List<EmployeesJSONExportDTO> topEmployeesExport();
}
