package org.softuni.mostwanted.Service.api;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.softuni.mostwanted.domain.dto.DistinctImportDTO;

public interface DistrictService {

    void create(DistinctImportDTO dto);
}
