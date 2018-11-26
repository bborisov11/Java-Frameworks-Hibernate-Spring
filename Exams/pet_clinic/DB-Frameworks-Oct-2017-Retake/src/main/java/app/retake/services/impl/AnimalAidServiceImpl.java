package app.retake.services.impl;


import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.models.AnimalAid;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalAidRepository;
import app.retake.repositories.ProcedureRepository;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;

@Service
@Transactional
public class AnimalAidServiceImpl implements AnimalAidService {

    private final AnimalAidRepository animalAidRepository;
    private final ModelParser modelParser;
    private final ProcedureRepository procedureRepository;
    private final Validator validator;
    @Autowired
    public AnimalAidServiceImpl(AnimalAidRepository animalAidRepository, ModelParser modelParser, ProcedureRepository procedureRepository, Validator validator) {
        this.animalAidRepository = animalAidRepository;
        this.modelParser = modelParser;
        this.procedureRepository = procedureRepository;
        this.validator = validator;
    }

    @Override
    public void create(AnimalAidJSONImportDTO dto) {

        if(this.validator.validate(dto).size() != 0) {
            throw new IllegalArgumentException();
        }
        AnimalAid currentAnimalAid = this.animalAidRepository.findByName(dto.getName());
        if(currentAnimalAid != null) {
            if(currentAnimalAid.getPrice().equals(dto.getPrice())) {
                return;
            }
            currentAnimalAid.setPrice(dto.getPrice());

        } else {
            currentAnimalAid = this.modelParser.convert(dto, AnimalAid.class);

        }
        this.animalAidRepository.save(currentAnimalAid);
    }
}
