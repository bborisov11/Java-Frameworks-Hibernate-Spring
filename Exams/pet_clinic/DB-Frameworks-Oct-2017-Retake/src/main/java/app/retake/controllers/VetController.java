package app.retake.controllers;

import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.VetService;
import com.google.gson.annotations.Expose;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
@Controller
public class VetController {
    private Parser XMLParser;
    private VetService vetService;

    public VetController(@Qualifier("XMLParser") Parser xmlParser, VetService vetService) {
        XMLParser = xmlParser;
        this.vetService = vetService;
    }

    public String importDataFromXML(String xmlContent){
        StringBuilder builder = new StringBuilder();
        VetWrapperXMLImportDTO vetDTO = null;
        try {
             vetDTO = this.XMLParser.read(VetWrapperXMLImportDTO.class, xmlContent);
        } catch (Exception e) {
            e.printStackTrace();
    }
            for (VetXMLImportDTO vetXMLImportDTO : vetDTO.getVets()) {
                try {
                    this.vetService.create(vetXMLImportDTO);
                    builder.append(String.format("Record %s successfully imported.",
                            vetXMLImportDTO.getName()));
                    builder.append(System.lineSeparator());
                } catch (Exception e) {
                    builder.append("Error: Invalid data.");
                    builder.append(System.lineSeparator());
                }
            }

        return builder.toString();
    }
}
