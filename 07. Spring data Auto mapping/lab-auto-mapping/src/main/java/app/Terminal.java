package app;

import app.dtos.EmployeeDto;
import app.dtos.ManagerDto;
import app.entities.Address;
import app.entities.Employee;
import app.repositories.AddressRepository;
import app.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class Terminal implements CommandLineRunner {

    //field interface service
    //@autowired constructor
    //run method

    private AddressRepository addressRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public Terminal(AddressRepository addressRepository, EmployeeRepository employeeRepository) {

        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Address address = new Address();
        // Employee employee = new Employee();
        //Employee employee = this.employeeRepository.findById(1);
        Optional<Employee> employee = this.employeeRepository.findById(1);

        List<Employee> managers = this.employeeRepository.findByManagerIsNotNull();

        //for (Employee manager : managers) {
          //  System.out.println(manager.getFirstName());
        //    for (Employee employee1 : manager.getEmployees()) {
         //       System.out.println(employee1.getFirstName());
         //   }
        //}

        Employee first = new Employee();

        if(employee.isPresent()) {
            first = employee.get();
        }

        ModelMapper mapper = new ModelMapper();
        EmployeeDto dto = mapper.map(first, EmployeeDto.class);
        //System.out.println(dto.getFirstName()+" "+dto.getLastName());
        ManagerDto managerDto = mapper.map(first, ManagerDto.class);

      //  for (EmployeeDto emp : managerDto.getEmployees()) {
        //    System.out.print(emp.getFirstName()+" "+emp.getLastName()+" | Employees: ");
      //  }
        List<Employee> emp = this.employeeRepository.findByBirthdateAfter(LocalDate.parse("1990-01-01"));

        for (Employee employee1 : emp) {
            System.out.println(employee1.getFirstName());
        }
        //addressRepository.save(address);
        //employeeRepository.save(employee);
    }
}

