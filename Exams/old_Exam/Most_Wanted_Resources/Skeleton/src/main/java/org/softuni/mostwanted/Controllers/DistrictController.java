package org.softuni.mostwanted.Controllers;

import org.softuni.mostwanted.Service.api.DistrictService;
import org.softuni.mostwanted.domain.dto.DistinctImportDTO;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class DistrictController {

    private final Parser jsonParser;
    private final DistrictService districtService;

    @Autowired
    public DistrictController(@Qualifier("JSONParser") Parser jsonParser, DistrictService districtService) {
        this.jsonParser = jsonParser;
        this.districtService = districtService;
    }

    public String importDataFromJSON(String jsonContent) throws IOException {
        DistinctImportDTO[] models = null;
        try {
            models = jsonParser.read(DistinctImportDTO[].class, jsonContent);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (DistinctImportDTO model : models) {
            try {
                districtService.create(model);
                sb.append(String.format("Successfully imported Distinct – %s.", model.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Incorrect Data!");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

}
