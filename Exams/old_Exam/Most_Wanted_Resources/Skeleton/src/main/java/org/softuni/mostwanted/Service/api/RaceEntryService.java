package org.softuni.mostwanted.Service.api;

import org.softuni.mostwanted.domain.dto.RaceEntriesXMLImportDTO;

public interface RaceEntryService {
    Integer create(RaceEntriesXMLImportDTO dto);
}
