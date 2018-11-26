package org.softuni.mostwanted.Service.api;

import org.softuni.mostwanted.domain.dto.RacerJSONExportDTO;
import org.softuni.mostwanted.domain.dto.RacerJSONImportDTO;

import javax.management.InvalidAttributeValueException;
import java.util.List;

public interface RacerService {
    void create(RacerJSONImportDTO dto) throws InvalidAttributeValueException;

    List<RacerJSONExportDTO> exportRacersWithAnyCars();
}
