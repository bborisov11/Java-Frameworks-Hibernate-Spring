package org.softuni.mostwanted.Service.impl;

import org.softuni.mostwanted.Service.api.RacerService;
import org.softuni.mostwanted.domain.dto.CarJSONExportDTO;
import org.softuni.mostwanted.domain.dto.RacerJSONExportDTO;
import org.softuni.mostwanted.domain.dto.RacerJSONImportDTO;
import org.softuni.mostwanted.domain.models.Racer;
import org.softuni.mostwanted.domain.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import javax.validation.Validator;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
//@Transactional
public class RacerServiceImpl implements RacerService {

    private final ModelParser mapper;
    private final Validator validator;
    private final RacerRepository racerRepository;
    private final TownRepository townRepository;

    @Autowired
    public RacerServiceImpl(ModelParser mapper, Validator validator, RacerRepository racerRepository, TownRepository townRepository) {
        this.mapper = mapper;
        this.validator = validator;

        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
    }

    @Override
    public void create(RacerJSONImportDTO dto) throws InvalidAttributeValueException {

        if (validator.validate(dto).size() != 0) {
            throw new IllegalArgumentException();
        }
        Racer service = racerRepository.findByName(dto.getName());
        Town town = townRepository.findByName(dto.getHomeTown());
        if (service != null) {
            throw new InvalidAttributeValueException();
            //throw new IllegalArgumentException();
        } else {
            service = mapper.convert(dto, Racer.class);
            service.setName(dto.getName());
            service.setAge(dto.getAge());
            service.setBounty(dto.getBounty());
            service.setHomeTown(town);
        }
        racerRepository.save(service);
    }

    @Override
    public  List<RacerJSONExportDTO> exportRacersWithAnyCars() {
      //  List<RacerJSONExportDTO> racers = racerRepository.findAll();
     //          .stream()
     //          .filter(r -> r.getCars().size() > 0)
     //          .map(x -> x.getAge() != null ? new RacerJSONExportDTO(x.getName(),x.getAge(),
     //  //                x.getCars()
     // //                         .stream()
     //   //                       .map(c -> String.format("%s %s %s",
     //       //                           c.getBrand(), c.getModel(), c.getYearOfProduction())).collect(Collectors.toList()))
     //                  : new RacerJSONExportDTO(x.getName(),x.getCars()
     //                  .stream()
     //                  .map(c -> new CarJSONExportDTO(c.getText()))
     //                  .collect(Collectors.toList())))
     //          .sorted(Comparator.comparing((RacerJSONExportDTO x) -> x.getCars().size()
     //                  ,Comparator.reverseOrder())
     //                  .thenComparing(RacerJSONExportDTO::getName))
     //          .collect(Collectors.toList());

        return null;
    }
}
