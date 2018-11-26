package app.exam.controller;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class EmployeesController {
    private Parser parser;
    private EmployeeService employeeService;
    @Autowired
    public EmployeesController(@Qualifier("JSONParser") Parser parser, EmployeeService employeeService) {
        this.parser = parser;
        this.employeeService = employeeService;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder builder = new StringBuilder();
        EmployeeJSONImportDTO[] dtos = null;
        try {
             dtos = this.parser.read(EmployeeJSONImportDTO[].class,
                    jsonContent);
        } catch (JAXBException | IOException e1) {
            e1.printStackTrace();
        }
        for (EmployeeJSONImportDTO dto : dtos) {
            try {
                this.employeeService.create(dto);
                builder.append(String.format("Record %s successfully imported.", dto.getName()));
                builder.append(System.lineSeparator());
            } catch (Exception e) {
                builder.append("Error: Invalid data.");
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
