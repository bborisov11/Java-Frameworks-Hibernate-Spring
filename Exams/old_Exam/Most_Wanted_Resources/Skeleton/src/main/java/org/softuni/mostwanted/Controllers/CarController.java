package org.softuni.mostwanted.Controllers;

import org.softuni.mostwanted.Service.api.CarService;
import org.softuni.mostwanted.domain.dto.CarJSONImportDTO;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class CarController {

    private final Parser jsonParser;
    private final CarService carService;

    @Autowired
    public CarController(@Qualifier("JSONParser") Parser jsonParser, CarService carService) {
        this.jsonParser = jsonParser;
        this.carService = carService;
    }

    public String importDataFromJSON(String jsonContent) throws IOException {
        CarJSONImportDTO[] models = null;
        try {
            models = jsonParser.read(CarJSONImportDTO[].class, jsonContent);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (CarJSONImportDTO model : models) {
            try {
                carService.create(model);
                sb.append(String.format("%s %s @ %d",model.getBrand(),model.getModel(),model.getYearOfProduction()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Incorrect Data!");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }


}
