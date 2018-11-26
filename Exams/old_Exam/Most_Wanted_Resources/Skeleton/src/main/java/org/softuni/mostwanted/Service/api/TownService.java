package org.softuni.mostwanted.Service.api;

import org.softuni.mostwanted.domain.dto.TownJSONExportDTO;
import org.softuni.mostwanted.domain.dto.TownJSONImportDTO;

import java.util.List;

public interface TownService {

    void create(TownJSONImportDTO dto);

    List<TownJSONExportDTO> townWithAnyRacers();
}
