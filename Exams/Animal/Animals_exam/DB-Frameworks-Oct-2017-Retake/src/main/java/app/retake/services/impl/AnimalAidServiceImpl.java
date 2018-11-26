package app.retake.services.impl;


import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.models.AnimalAid;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalAidRepository;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;

@Service
@Transactional
public class AnimalAidServiceImpl implements AnimalAidService {

    private final ModelParser mapper;
    private final Validator validator;
    private final AnimalAidRepository animalAidRepository;

    @Autowired
    public AnimalAidServiceImpl(ModelParser mapper,
                                Validator validator,
                                AnimalAidRepository animalAidRepository) {
        this.mapper = mapper;
        this.validator = validator;
        this.animalAidRepository = animalAidRepository;
    }

    @Override
    public void create(AnimalAidJSONImportDTO dto) {
        if (validator.validate(dto).size() != 0){
            throw new IllegalArgumentException();
        }
        AnimalAid service = animalAidRepository.findByName(dto.getName());
        if (service != null) {
            if (service.getPrice().equals(dto.getPrice())){
                return; //not specified if they want error message
            } else {
                service.setPrice(dto.getPrice());
            }
        } else {
            service = mapper.convert(dto, AnimalAid.class);
        }
        animalAidRepository.save(service);
    }
}
