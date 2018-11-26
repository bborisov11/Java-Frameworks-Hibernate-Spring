package org.softuni.ruk.controllers;

import org.softuni.ruk.domain.dto.ClientJSONImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.service.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ClientController {

    private final Parser jsonParser;
    private final ClientService clientService;

    @Autowired
    public ClientController(@Qualifier("JSONParser") Parser jsonParser, ClientService clientService) {
        this.jsonParser = jsonParser;
        this.clientService = clientService;
    }

    public String importDataFromJSON(String jsonContent) throws IOException {
        ClientJSONImportDTO[] models = null;
        try {
            models = jsonParser.read(ClientJSONImportDTO[].class, jsonContent);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (ClientJSONImportDTO model : models) {
            try {
                clientService.create(model);
                sb.append(String.format("Succesfully imported Employee – %s %s.",model.getFirst_name(),model.getLast_name()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Incorrect Data!");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
