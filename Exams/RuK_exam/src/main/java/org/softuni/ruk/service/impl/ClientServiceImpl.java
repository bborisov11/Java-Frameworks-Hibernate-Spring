package org.softuni.ruk.service.impl;

import org.softuni.ruk.domain.dto.ClientJSONImportDTO;
import org.softuni.ruk.domain.models.Branch;
import org.softuni.ruk.domain.models.Client;
import org.softuni.ruk.domain.models.Employee;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.BranchRepository;
import org.softuni.ruk.repositories.ClientRepository;
import org.softuni.ruk.repositories.EmployeeRepository;
import org.softuni.ruk.service.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ModelParser mapper;
    private final Validator validator;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public ClientServiceImpl(ModelParser mapper,
                             Validator validator,
                             ClientRepository clientRepository, EmployeeRepository employeeRepository, BranchRepository branchRepository) {
        this.mapper = mapper;
        this.validator = validator;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
    }

   @Override
   public void create(ClientJSONImportDTO dto) {
       if (validator.validate(dto).size() != 0){
           throw new IllegalArgumentException();
       }

       Client service = mapper.convert(dto,Client.class);

       String fullName = dto.getFirst_name()+" "+dto.getLast_name();
       service.setFullName(fullName);
       Employee employee = employeeRepository.findByName(fullName);
       service.setAge(dto.getAge());
       String[] employeeNames = dto.getAppointed_employee().split(" ");
       employee.setFirstName(employeeNames[0]);
       employee.setLastName(employeeNames[1]);
       service.getEmployees().add(employee);

       //employeeRepository.save(employee);
       clientRepository.save(service);

   }

}
