package org.softuni.ruk.service.api;

import org.softuni.ruk.domain.dto.BranchJSONImportDTO;

public interface BranchService {
    void create(BranchJSONImportDTO dto);
}
