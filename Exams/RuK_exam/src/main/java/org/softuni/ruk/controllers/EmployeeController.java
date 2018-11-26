package org.softuni.ruk.controllers;

import org.softuni.ruk.domain.dto.EmployeeJSONImportDTO;
import org.softuni.ruk.domain.models.Employee;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class EmployeeController {

    private final Parser jsonParser;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(@Qualifier("JSONParser") Parser jsonParser, EmployeeService employeeService) {
        this.jsonParser = jsonParser;
        this.employeeService = employeeService;
    }

    public String importDataFromJSON(String jsonContent) throws IOException {
        EmployeeJSONImportDTO[] models = null;
        try {
            models = jsonParser.read(EmployeeJSONImportDTO[].class, jsonContent);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (EmployeeJSONImportDTO model : models) {
            try {
                employeeService.create(model);
                sb.append(String.format("Succesfully imported Employee – %s.",model.getFull_name()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Incorrect Data!");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public void generateCategories() {
        employeeService.generateClients();
    }

    public String exportEmployees() throws IOException, JAXBException {
        try {
            return jsonParser.write(employeeService.topEmployeesExport());
        }
        catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
