package org.softuni.mostwanted.Service.impl;

import org.softuni.mostwanted.Service.api.TownService;
import org.softuni.mostwanted.domain.dto.TownJSONExportDTO;
import org.softuni.mostwanted.domain.dto.TownJSONImportDTO;
import org.softuni.mostwanted.domain.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Transactional
public class TownServiceImpl implements TownService {

    private final ModelParser mapper;
    private final Validator validator;
    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(ModelParser mapper, Validator validator, TownRepository townRepository) {
        this.mapper = mapper;
        this.validator = validator;
        this.townRepository = townRepository;
    }

    @Override
    public void create(TownJSONImportDTO dto) {

        if (validator.validate(dto).size() != 0) {
            throw new IllegalArgumentException();
        }
        Town service = townRepository.findByName(dto.getName());
        if (service != null) {
            throw new IllegalArgumentException();
        } else {
            service = new Town();
            service.setName(dto.getName());
            service = mapper.convert(dto, Town.class);
        }
        townRepository.save(service);
    }
    @Override
    public List<TownJSONExportDTO> townWithAnyRacers() {

        return townRepository.findAll()
                .stream()
                .filter(t -> t.getRacers().size() > 0)
                .map(t -> new TownJSONExportDTO(t.getName(),t.getRacers().size()))
                .sorted(Comparator.comparing(TownJSONExportDTO::getRacers,Comparator.reverseOrder())
                        .thenComparing(TownJSONExportDTO::getName))
                .collect(Collectors.toList());
    }

}

