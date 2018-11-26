package app.retake.services.impl;

import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.domain.models.Vet;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.VetRepository;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;

@Service
@Transactional
public class VetServiceImpl implements VetService {
    private VetRepository vetRepository;
    private Validator validation;
    private ModelParser modelParser;
    @Autowired
    public VetServiceImpl(VetRepository vetRepository, Validator validation, ModelParser modelParser) {
        this.vetRepository = vetRepository;
        this.validation = validation;
        this.modelParser = modelParser;
    }


    @Override
    public void create(VetXMLImportDTO dto) {
        if(validation.validate(dto).size() != 0) {
            throw new IllegalArgumentException();
        }
        Vet vet = this.modelParser.convert(dto, Vet.class);
        this.vetRepository.save(vet);
    }
}
