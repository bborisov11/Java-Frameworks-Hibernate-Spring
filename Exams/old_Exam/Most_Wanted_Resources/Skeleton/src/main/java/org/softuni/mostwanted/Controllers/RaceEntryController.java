package org.softuni.mostwanted.Controllers;

import org.softuni.mostwanted.Service.api.RaceEntryService;
import org.softuni.mostwanted.domain.dto.RaceEntriesXMLImportDTO;
import org.softuni.mostwanted.domain.dto.RaceEntriesXMLwrapperImportDTO;
import org.softuni.mostwanted.domain.models.RaceEntry;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceEntryController {

    private final Parser xmlParser;
    private final RaceEntryService raceEntryService;

    public RaceEntryController(@Qualifier("XMLParser") Parser xmlParser,
                               RaceEntryService raceEntryService) {
        this.xmlParser = xmlParser;
        this.raceEntryService = raceEntryService;
    }

    public String importDataFromXML(String xmlContent){

        RaceEntriesXMLwrapperImportDTO wrapper = null;
        try {
            wrapper = xmlParser.read(RaceEntriesXMLwrapperImportDTO.class, xmlContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (RaceEntriesXMLImportDTO model : wrapper.getEntries()) {
            try {
                 Integer id =   raceEntryService.create(model);
                sb.append("Record successfully imported "+ id);
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                e.printStackTrace();
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
