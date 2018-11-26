package org.softuni.ruk.controllers;

import org.softuni.ruk.domain.dto.BranchJSONImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.service.api.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class BranchController {

    private final Parser jsonParser;
    private final BranchService branchService;

    @Autowired
    public BranchController(@Qualifier("JSONParser") Parser jsonParser, BranchService branchService) {
        this.jsonParser = jsonParser;
        this.branchService = branchService;
    }

    public String importDataFromJSON(String jsonContent) throws IOException {
        BranchJSONImportDTO[] models = null;
        try {
            models = jsonParser.read(BranchJSONImportDTO[].class, jsonContent);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (BranchJSONImportDTO model : models) {
            try {
                branchService.create(model);
                sb.append(String.format("Succesfully imported Branch – %s.", model.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Incorrect Data!");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
