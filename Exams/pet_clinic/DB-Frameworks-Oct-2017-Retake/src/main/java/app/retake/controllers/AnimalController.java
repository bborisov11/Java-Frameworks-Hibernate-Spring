package app.retake.controllers;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalService;
import jdk.nashorn.internal.runtime.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnimalController {
    private Parser jsonParser;
    private AnimalService animalService;
    @Autowired
    public AnimalController(@Qualifier("JSONParser") Parser jsonParser, AnimalService animalService) {
        this.jsonParser = jsonParser;
        this.animalService = animalService;
    }


    public String importDataFromJSON(String jsonContent) {
        StringBuilder builder = new StringBuilder();
        AnimalJSONImportDTO[] dtos = null;
        try {
            dtos = this.jsonParser.read(AnimalJSONImportDTO[].class, jsonContent);
        }
        catch (ParserException | IOException | JAXBException e) {
            e.printStackTrace();
        }
        for (AnimalJSONImportDTO dto : dtos) {
                try {
                    this.animalService.create(dto);
                    builder.append(String.format("Record %s Passport №: %s successfully imported.",
                            dto.getName(), dto.getPassport().getSerialNumber()));
                    builder.append(System.lineSeparator());
                }
               catch (Exception e) {
                        builder.append("Error: Invalid data.");
                        builder.append(System.lineSeparator());
                    }
        }
        return builder.toString();
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) {
        StringBuilder builder = new StringBuilder();
        try {
            builder.append(this.jsonParser.write(this.animalService.findByOwnerPhoneNumber(phoneNumber)));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
