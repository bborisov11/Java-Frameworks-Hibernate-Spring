package org.softuni.mostwanted.Service.api;

import org.softuni.mostwanted.domain.dto.CarJSONImportDTO;

public interface CarService {
    void create(CarJSONImportDTO dto);
}
