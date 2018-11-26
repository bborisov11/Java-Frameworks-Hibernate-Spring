package app.exam.terminal;

import app.exam.controller.EmployeesController;
import app.exam.controller.ItemsController;
import app.exam.controller.OrdersController;
import app.exam.io.interfaces.ConsoleIO;
import app.exam.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static app.exam.Config.*;

@Component
public class Terminal implements CommandLineRunner {

    private EmployeesController employeesController;
    private ItemsController itemsController;
    private OrdersController ordersController;
    private FileIO fileIO;
    private ConsoleIO consoleIO;

    @Autowired
    public Terminal(EmployeesController employeesController, ItemsController itemsController, OrdersController ordersController, FileIO fileIO, ConsoleIO consoleIO) {
        this.employeesController = employeesController;
        this.itemsController = itemsController;
        this.ordersController = ordersController;
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
    }

    @Override
    public void run(String... args) throws Exception {
        consoleIO.write(this.employeesController.importDataFromJSON(fileIO.read(JSON_IMPORT_EMPLOYEES)));
        consoleIO.write(this.itemsController.importDataFromJSON(fileIO.read(JSON_IMPORT_ITEMS)));
        consoleIO.write(this.ordersController.importDataFromXML(fileIO.read(XML_IMPORT_ORDER)));
       // fileIO.write(this.ordersController
         //       .exportOrdersByEmployeeAndOrderType("Avery Rush", "ToGo"),
           //     JSON_EXPORT_ORDERS_BY_EMPLOYEE_NAME_AND_ORDER_TYPE);
    }
}
