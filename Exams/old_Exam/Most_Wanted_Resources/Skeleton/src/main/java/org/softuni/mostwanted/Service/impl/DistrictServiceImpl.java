package org.softuni.mostwanted.Service.impl;

import org.softuni.mostwanted.Service.api.DistrictService;
import org.softuni.mostwanted.domain.dto.DistinctImportDTO;
import org.softuni.mostwanted.domain.models.District;
import org.softuni.mostwanted.domain.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;

@Service
//@Transactional
public class DistrictServiceImpl implements DistrictService {

    private final ModelParser mapper;
    private final Validator validator;
    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;

    public DistrictServiceImpl(ModelParser mapper, Validator validator, DistrictRepository districtRepository, TownRepository townRepository) {
        this.mapper = mapper;
        this.validator = validator;
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
    }

    @Override
    public void create(DistinctImportDTO dto) {

        if (validator.validate(dto).size() != 0) {
            throw new IllegalArgumentException();
        }
        District service = districtRepository.findByName(dto.getName());
        Town town = townRepository.findByName(dto.getTownName());
        if (service != null) {
            throw new IllegalArgumentException();
        } else {
            service = mapper.convert(dto, District.class);
           // service.setName(dto.getName());
            service.setTown(town);
        }
        districtRepository.save(service);
    }
}
