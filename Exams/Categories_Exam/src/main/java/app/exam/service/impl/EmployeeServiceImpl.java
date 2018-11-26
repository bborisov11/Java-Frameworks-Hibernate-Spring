package app.exam.service.impl;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.domain.entities.Employee;
import app.exam.domain.entities.Position;
import app.exam.parser.interfaces.ModelParser;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.PositionRepository;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelParser parser;
    private final PositionRepository positionRepository;
    private final EmployeeRepository employeeRepository;
    private final Validator validation;

    @Autowired
    public EmployeeServiceImpl(ModelParser parser, PositionRepository positionRepository, EmployeeRepository employeeRepository, Validator validation) {
        this.parser = parser;
        this.positionRepository = positionRepository;
        this.employeeRepository = employeeRepository;
        this.validation = validation;
    }

    @Override
    public void create(EmployeeJSONImportDTO importDTO) {

        if(validation.validate(importDTO).size() != 0) {
            throw new IllegalArgumentException();
        }

        Employee employee = this.parser.convert(importDTO, Employee.class);
        Position position = this.positionRepository.findByName(importDTO.getName());

        if(position == null) {
            position = new Position(importDTO.getName());
        }
        position.getEmployees().add(employee);
        employee.setPosition(position);

        this.positionRepository.save(position);
        this.employeeRepository.save(employee);
    }

    @Override
    public void createMany(EmployeeJSONImportDTO[] importDTO) {

    }
}
