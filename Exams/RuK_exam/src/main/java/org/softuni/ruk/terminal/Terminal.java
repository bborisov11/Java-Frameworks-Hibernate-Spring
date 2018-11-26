package org.softuni.ruk.terminal;

import org.softuni.ruk.controllers.BranchController;
import org.softuni.ruk.controllers.ClientController;
import org.softuni.ruk.controllers.EmployeeController;
import org.softuni.ruk.io.interfaces.ConsoleIO;
import org.softuni.ruk.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static org.softuni.ruk.Config.*;

@Component
//@Transactional
public class Terminal implements CommandLineRunner {


    private final ConsoleIO consoleIO;
    private final FileIO fileIO;
    private final BranchController branchController;
    private final EmployeeController employeeController;
    private final ClientController clientController;

    @Autowired
    public Terminal(ConsoleIO consoleIO, FileIO fileIO, BranchController branchController, EmployeeController employeeController, ClientController clientController) {
        this.consoleIO = consoleIO;
        this.fileIO = fileIO;
        this.branchController = branchController;
        this.employeeController = employeeController;
        this.clientController = clientController;
    }

    @Override
    public void run(String... args) throws Exception {
        
       //consoleIO.write(branchController.importDataFromJSON(fileIO.read(BRANCH_JSON_IMPORT)));
       //consoleIO.write(employeeController.importDataFromJSON(fileIO.read(EMPLOYEE_JSON_IMPORT)));
       //consoleIO.write(clientController.importDataFromJSON(fileIO.read(CLIENT_JSON_IMPORT)));
        fileIO.write(employeeController.exportEmployees(),EMPLOYEE_JSON_EXPORT);
        //employeeController.generateCategories();

    }
}
