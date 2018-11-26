package app.exam.controller;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ItemsController {
    private Parser parser;
    private ItemsService itemsService;

    public ItemsController(@Qualifier("JSONParser") Parser parser, ItemsService itemsService) {
        this.parser = parser;
        this.itemsService = itemsService;
    }

    public String importDataFromJSON(String jsonContent) {
        ItemJSONImportDTO[] dtos = null;
        StringBuilder builder = new StringBuilder();
        try {
             dtos = this.parser.read(ItemJSONImportDTO[].class, jsonContent);
            for (ItemJSONImportDTO dto : dtos) {
                try {
                    this.itemsService.create(dto);
                    builder.append(String.format("Record %s successfully imported.", dto.getName()));
                    builder.append(System.lineSeparator());
                } catch (Exception e) {
                    builder.append("Error: Invalid data.");
                    builder.append(System.lineSeparator());
                }
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
