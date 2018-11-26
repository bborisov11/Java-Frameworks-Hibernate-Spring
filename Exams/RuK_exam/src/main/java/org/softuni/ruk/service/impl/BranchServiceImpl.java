package org.softuni.ruk.service.impl;

import org.softuni.ruk.domain.dto.BranchJSONImportDTO;
import org.softuni.ruk.domain.models.Branch;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.service.api.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.softuni.ruk.repositories.BranchRepository;


import javax.transaction.Transactional;
import javax.validation.Validator;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    private final ModelParser mapper;
    private final Validator validator;
    private final BranchRepository branchRepository;

    @Autowired
    public BranchServiceImpl(ModelParser mapper,
                                Validator validator,
                                BranchRepository branchRepository) {
        this.mapper = mapper;
        this.validator = validator;
        this.branchRepository = branchRepository;
    }

    @Override
    public void create(BranchJSONImportDTO dto) {
        if (validator.validate(dto).size() != 0){
            throw new IllegalArgumentException();
        }
            Branch service = mapper.convert(dto,Branch.class);
            service.setName(dto.getName());

            branchRepository.save(service);
        }
}
