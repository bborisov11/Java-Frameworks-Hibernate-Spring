package org.softuni.ruk.service.impl;

import org.softuni.ruk.domain.dto.EmployeeJSONImportDTO;
import org.softuni.ruk.domain.dto.EmployeesJSONExportDTO;
import org.softuni.ruk.domain.models.Branch;
import org.softuni.ruk.domain.models.Client;
import org.softuni.ruk.domain.models.Employee;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.BranchRepository;
import org.softuni.ruk.repositories.ClientRepository;
import org.softuni.ruk.repositories.EmployeeRepository;
import org.softuni.ruk.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final ModelParser mapper;
    private final Validator validator;
    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public EmployeeServiceImpl(ModelParser mapper,
                               Validator validator,
                               EmployeeRepository employeeRepository, BranchRepository branchRepository, ClientRepository clientRepository) {
        this.mapper = mapper;
        this.validator = validator;
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void create(EmployeeJSONImportDTO dto) {
        if (validator.validate(dto).size() != 0){
            throw new IllegalArgumentException();
        }

        Employee service = mapper.convert(dto,Employee.class);
        Branch branch = mapper.convert(dto.getBranch_name(),Branch.class);
       String[] fullName = dto.getFull_name().split(" ");
       String firstName = fullName[0];
       String lastName = fullName[1];
       service.setFirstName(firstName);
       service.setLastName(lastName);
       service.setSalary(dto.getSalary());
        LocalDate dt = LocalDate.parse(dto.getStarted_on());
       service.setStartedOn(dt);
       service.setBranch(branch);
       service.getBranch().setName(dto.getBranch_name());
        branchRepository.save(branch);
        employeeRepository.save(service);
    }

    @Override
    public void generateClients() {

        Random random = new Random();
        List<Employee> employees = employeeRepository.findAll();
        long clientCount = clientRepository.count();
        for (Employee employee : employees) {
            Client client = clientRepository.findById(1); //random.nextInt((int)clientCount) + 1
            client.getEmployees().add(employee);
            clientRepository.save(client);
        }
    }

   @Override
   public List<EmployeesJSONExportDTO> topEmployeesExport() {
       return employeeRepository.findAll()
               .stream()
               .map(e -> new EmployeesJSONExportDTO(
                       String.format(e.getFirstName()+" "+e.getLastName()),e.getSalary(),e.getStartedOn().toString(),e.getClients()
               .stream()
               .map(Client::getFullName).collect(Collectors.toSet())))
               .sorted(Comparator.comparing((EmployeesJSONExportDTO s) -> s.getClients().size(), Comparator.reverseOrder())
               .thenComparing(EmployeesJSONExportDTO::getId))
               .collect(Collectors.toList());
   }
}
