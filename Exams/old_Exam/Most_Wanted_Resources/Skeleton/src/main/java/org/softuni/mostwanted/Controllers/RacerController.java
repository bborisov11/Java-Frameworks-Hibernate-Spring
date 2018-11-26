package org.softuni.mostwanted.Controllers;

import org.softuni.mostwanted.Service.api.RacerService;
import org.softuni.mostwanted.domain.dto.RacerJSONImportDTO;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.management.InvalidAttributeValueException;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RacerController {

    private final Parser jsonParser;
    private final RacerService racerService;

    @Autowired
    public RacerController(@Qualifier("JSONParser") Parser jsonParser, RacerService racerService) {
        this.jsonParser = jsonParser;
        this.racerService = racerService;
    }

    public String importDataFromJSON(String jsonContent) throws IOException {
        RacerJSONImportDTO[] models = null;
        try {
            models = jsonParser.read(RacerJSONImportDTO[].class, jsonContent);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (RacerJSONImportDTO model : models) {
            try {
                racerService.create(model);
                sb.append(String.format("Successfully imported %s – %s.","Racer",model.getName()));
                sb.append(System.lineSeparator());
            } catch(InvalidAttributeValueException e) {
                sb.append("Error: Duplicate Data!");
                sb.append(System.lineSeparator());
            }
            catch (Exception e) {
                sb.append("Error: Incorrect Data!");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String exportRacersWithAnyCars() {
        try {
            return jsonParser.write(racerService.exportRacersWithAnyCars());
        }
        catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
