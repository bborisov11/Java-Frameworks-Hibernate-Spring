package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.AnimalController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.io.api.ConsoleIO;
import app.retake.io.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static app.retake.Config.*;

@Component
public class Terminal implements CommandLineRunner {
    private AnimalAidController animalAidController;
    private AnimalController animalController;
    private VetController vetController;
    private ProcedureController procedureController;
    private ConsoleIO consoleIO;
    private FileIO fileIO;
    @Autowired
    public Terminal(AnimalAidController animalAidController, AnimalController animalController,
                    VetController vetController, ProcedureController procedureController, ConsoleIO consoleIO, FileIO fileIO) {
        this.animalAidController = animalAidController;
        this.animalController = animalController;
        this.vetController = vetController;
        this.procedureController = procedureController;
        this.consoleIO = consoleIO;
        this.fileIO = fileIO;
    }

    @Override
    public void run(String... strings) throws Exception {
       // this.consoleIO.write(this.animalAidController.importDataFromJSON(fileIO.read(ANIMAL_AIDS_IMPORT_JSON)));
       // this.consoleIO.write(this.animalController.importDataFromJSON(fileIO.read(ANIMALS_IMPORT_JSON)));
       // this.consoleIO.write(this.vetController.importDataFromXML(fileIO.read(VETS_IMPORT_XML)));
       // this.consoleIO.write(this.procedureController.importDataFromXML(fileIO.read(PROCEDURES_IMPORT_XML)));
        //this.fileIO.write(this.animalController.exportAnimalsByOwnerPhoneNumber("0887446123"), EXPORT_ANIMALS_BY_PHONENUMBER);
        this.fileIO.write(this.procedureController.exportProcedures(), EXPORT_XML_PROCEDURES);
    }
}
