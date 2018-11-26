package org.softuni.mostwanted.Controllers;

import org.softuni.mostwanted.Service.api.TownService;
import org.softuni.mostwanted.domain.dto.TownJSONImportDTO;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
@Transactional
public class TownController {

    private final Parser jsonParser;
    private final TownService townService;

    @Autowired
    public TownController(@Qualifier("JSONParser") Parser jsonParser, TownService townService, TownService townService1) {

        this.jsonParser = jsonParser;
        this.townService = townService1;
    }

    public String importDataFromJSON(String jsonContent) throws IOException {
        TownJSONImportDTO[] models = null;
        try {
            models = jsonParser.read(TownJSONImportDTO[].class, jsonContent);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (TownJSONImportDTO model : models) {
            try {
                townService.create(model);
                sb.append(String.format("Successfully imported Town – %s.", model.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                e.printStackTrace();
                sb.append("Error: Incorrect Data!");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String exportTownsWithAnyRacers() {
        try {
            return jsonParser.write(townService.townWithAnyRacers());
        }
        catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
