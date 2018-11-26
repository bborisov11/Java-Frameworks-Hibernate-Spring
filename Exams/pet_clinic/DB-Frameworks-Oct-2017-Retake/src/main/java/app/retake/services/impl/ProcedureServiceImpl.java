package app.retake.services.impl;

import app.retake.domain.dto.*;
import app.retake.domain.models.*;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.*;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    private ProcedureRepository procedureRepository;
    private VetRepository vetRepository;
    private PassportRepository passportRepository;
    private AnimalAidRepository animalAidRepository;
    private AnimalRepository animalRepository;
    private ModelParser modelParser;
    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository, VetRepository vetRepository, PassportRepository passportRepository, AnimalAidRepository animalAidRepository, AnimalRepository animalRepository, ModelParser modelParser) {
        this.procedureRepository = procedureRepository;
        this.vetRepository = vetRepository;
        this.passportRepository = passportRepository;
        this.animalAidRepository = animalAidRepository;
        this.animalRepository = animalRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(ProcedureXMLImportDTO dto) throws ParseException {
        Vet vet = this.vetRepository.findByName(dto.getVet());
        Passport passport = this.passportRepository.findBySerialNumber(dto.getAnimal());
        if(vet == null || passport == null) {
            throw new IllegalArgumentException();
        }
        Procedure procedure = this.modelParser.convert(dto, Procedure.class);
        Set<AnimalAid> animalAids = new LinkedHashSet<>();
        for (ProcedureAnimalAidXMLImportDTO procedureAnimalAidXMLImportDTO : dto.getAnimalAids()) {
            AnimalAid animalAid = this.animalAidRepository.findByName(procedureAnimalAidXMLImportDTO.getName());
            if(animalAid != null) {
                animalAids.add(animalAid);
            }
            else {
                throw new IllegalArgumentException();
            }
        }

      //  Animal animal = this.animalRepository.findByPassport(passport);
        Animal animal = passport.getAnimal();
       // Procedure procedure = new Procedure(animalAids,vet, animal);

        procedure.setVet(vet);
        procedure.setAnimal(animal);
        procedure.setServices(animalAids);
        //procedure.setDate(passport.getRegistrationDate());
        animal.getProcedures().add(procedure);

        for (AnimalAid animalAid : animalAids) {
            animalAid.getProcedures().add(procedure);
        }
        this.procedureRepository.save(procedure);
        this.animalAidRepository.save(animalAids);


       // this.vetRepository.save(vet);
       // this.animalRepository.save(animal);

    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {
        List<ProcedureXMLExportDTO> procedures = this.procedureRepository.findAll()
                .stream()
                .map(x -> new ProcedureXMLExportDTO(x.getAnimal().getPassport().getSerialNumber(),
                        x.getAnimal().getPassport().getOwnerPhoneNumber(),
                        x.getServices()
                                .stream()
                                .map(y ->
                                        new ProcedureAnimalAidXMLExportDTO(y.getName(),y.getPrice()))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());

        return new ProcedureWrapperXMLExportDTO(procedures);
    }
}

