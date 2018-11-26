package app.retake.services.impl;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.dto.PassportJSONImportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository animalRepository;
    private Validator validator;
    private ModelParser modelParser;
    private PassportRepository passportRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository, Validator validator, ModelParser modelParser, PassportRepository passportRepository) {
        this.animalRepository = animalRepository;
        this.validator = validator;
        this.modelParser = modelParser;
        this.passportRepository = passportRepository;
    }

    @Override
    public void create(AnimalJSONImportDTO dto) throws ParseException {
        if(validator.validate(dto).size() != 0) {
            throw new IllegalArgumentException();
        }
        Animal animal = this.animalRepository.findByName(dto.getName());
        if(animal == null) {
           animal = this.modelParser.convert(dto, Animal.class);
           PassportJSONImportDTO passportDTO = dto.getPassport();
           Passport passport = this.modelParser.convert(passportDTO, Passport.class);
           animal.setPassport(passport);
            this.passportRepository.save(passport);
            this.animalRepository.save(animal);
        }
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        Set<Animal> animals = this.animalRepository.findByPassportOwnerPhoneNumber(phoneNumber);

        List<AnimalsJSONExportDTO> dtos = new ArrayList<>();
        for (Animal animal : animals) {
            AnimalsJSONExportDTO currentDTO = new AnimalsJSONExportDTO(animal.getPassport().getOwnerName(),
                    animal.getName(), animal.getAge(), animal.getPassport().getSerialNumber(),
                    animal.getPassport().getRegistrationDate());
            //AnimalsJSONExportDTO currentDTO = this.modelParser.convert(animal, AnimalsJSONExportDTO.class);
            dtos.add(currentDTO);
        }
        dtos.sort(Comparator.comparing(AnimalsJSONExportDTO::getAge)
                .thenComparing(AnimalsJSONExportDTO::getSerialNumber));
       // String asd ="";
        return dtos;
    }
}
