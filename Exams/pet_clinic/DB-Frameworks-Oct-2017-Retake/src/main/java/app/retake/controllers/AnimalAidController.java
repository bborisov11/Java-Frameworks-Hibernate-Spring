package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;

import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalAidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
@Controller
public class AnimalAidController {
    private AnimalAidService animalAidService;
    private Parser jsonParser;

    @Autowired
    public AnimalAidController(@Qualifier("JSONParser") Parser jsonParser, AnimalAidService animalAidService) {
        this.animalAidService = animalAidService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException {
        StringBuilder builder = new StringBuilder();
        try {
            AnimalAidJSONImportDTO[] dto = this.jsonParser.read(AnimalAidJSONImportDTO[].class, jsonContent);

            for (AnimalAidJSONImportDTO animalAidJSONImportDTO : dto) {
                try {
                    this.animalAidService.create(animalAidJSONImportDTO);
                    builder.append(String.format("Record %s successfully imported.",
                            animalAidJSONImportDTO.getName()));
                    builder.append(System.lineSeparator());
                } catch (Exception e) {
                    builder.append("Error: Invalid data.");
                    builder.append(System.lineSeparator());
                }
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
