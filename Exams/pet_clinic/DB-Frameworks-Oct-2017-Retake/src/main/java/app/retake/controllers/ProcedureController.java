package app.retake.controllers;

import app.retake.domain.dto.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.ProcedureWrapperXMLImportDTO;
import app.retake.domain.dto.ProcedureXMLImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class ProcedureController {

    private Parser parser;
    private ProcedureService procedureService;
    @Autowired
    public ProcedureController(@Qualifier("XMLParser") Parser parser, ProcedureService procedureService) {
        this.parser = parser;
        this.procedureService = procedureService;
    }

    public String importDataFromXML(String xmlContent){
        ProcedureWrapperXMLImportDTO wrapperDTO = null;
        StringBuilder builder = new StringBuilder();
        try {
            wrapperDTO = this.parser.read(ProcedureWrapperXMLImportDTO.class, xmlContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        for (ProcedureXMLImportDTO procedureXMLImportDTO : wrapperDTO.getProcedures()) {
            try {
                this.procedureService.create(procedureXMLImportDTO);
                builder.append("Record successfully imported.");
                builder.append(System.lineSeparator());
            } catch (Exception e) {
                builder.append("Error: Invalid data.");
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    public String exportProcedures() throws IOException, JAXBException {
        ProcedureWrapperXMLExportDTO wrapperDTO = this.procedureService.exportProcedures();
        return this.parser.write(wrapperDTO);
    }
}
