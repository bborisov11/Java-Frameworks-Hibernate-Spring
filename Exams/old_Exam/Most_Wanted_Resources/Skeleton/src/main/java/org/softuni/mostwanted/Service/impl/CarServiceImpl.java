package org.softuni.mostwanted.Service.impl;

import org.softuni.mostwanted.Service.api.CarService;
import org.softuni.mostwanted.domain.dto.CarJSONImportDTO;
import org.softuni.mostwanted.domain.models.Car;
import org.softuni.mostwanted.domain.models.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
//@Transactional
public class CarServiceImpl implements CarService {

    private final ModelParser mapper;
    private final Validator validator;
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;

    @Autowired
    public CarServiceImpl(ModelParser mapper, Validator validator, CarRepository carRepository, RacerRepository racerRepository) {
        this.mapper = mapper;
        this.validator = validator;
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
    }


    @Override
    public void create(CarJSONImportDTO dto) {

        if (validator.validate(dto).size() != 0) {
            throw new IllegalArgumentException();
        }

        Car service = carRepository.findByBrand(dto.getBrand());
        Racer racer = racerRepository.findByName(dto.getRacerName());

        if (service != null) {
            throw new IllegalArgumentException();
        } else {
            service = mapper.convert(dto, Car.class);
            service.setBrand(dto.getBrand());
            service.setModel(dto.getModel());
            service.setPrice(dto.getPrice());
            service.setYearOfProduction(dto.getYearOfProduction());
            service.setMaxSpeed(dto.getMaxSpeed());
            service.setZeroToSixty(dto.getZeroToSixty());
            service.setRacer(racer);
        }
        carRepository.save(service);
    }
}

