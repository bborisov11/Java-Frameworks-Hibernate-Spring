package org.softuni.mostwanted.Service.impl;

import org.softuni.mostwanted.Service.api.RaceEntryService;
import org.softuni.mostwanted.domain.dto.RaceEntriesXMLImportDTO;
import org.softuni.mostwanted.domain.models.RaceEntry;
import org.softuni.mostwanted.domain.models.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;

@Service
@Transactional
public class RaceEntryServiceImpl implements RaceEntryService {

    private final Validator validator;
    private final ModelParser mapper;
    private final RaceEntryRepository raceEntryRepository;
    private final RacerRepository racerRepository;

    public RaceEntryServiceImpl(Validator validator, ModelParser mapper, RaceEntryRepository raceEntryRepository, RacerRepository racerRepository) {
        this.validator = validator;
        this.mapper = mapper;
        this.raceEntryRepository = raceEntryRepository;
        this.racerRepository = racerRepository;
    }
    @Override
    public Integer create(RaceEntriesXMLImportDTO dto) {

            Racer racer = racerRepository.findByName(dto.getRacerName());

            if(validator.validate(dto).size() != 0) {
                throw new IllegalArgumentException();
            }
            RaceEntry raceEntry = mapper.convert(dto,RaceEntry.class);
            raceEntry.setRacer(racer);
         return raceEntryRepository.save(raceEntry).getId();

        }
}
