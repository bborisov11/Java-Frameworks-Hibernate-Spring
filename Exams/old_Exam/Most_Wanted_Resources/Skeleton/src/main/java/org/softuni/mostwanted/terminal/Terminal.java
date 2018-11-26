package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.Controllers.*;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.softuni.mostwanted.Config.*;


@Component
//@Transactional
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final TownController townController;
    private final CarController carController;
    private final DistrictController districtController;
    private final RacerController racerController;
    private final RaceEntryController raceEntryController;

    @Autowired
    public Terminal(FileIO fileIO, ConsoleIO consoleIO, TownController townController, CarController carController, DistrictController districtController, RacerController racerController, RaceEntryController raceEntryController) {
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.townController = townController;
        this.carController = carController;
        this.districtController = districtController;
        this.racerController = racerController;
        this.raceEntryController = raceEntryController;
    }

    @Override
    public void run(String... args) throws Exception {

      //  consoleIO.write(townController.importDataFromJSON(fileIO.read(TOWNS_IMPORT_JSON)));
      //  consoleIO.write(carController.importDataFromJSON(fileIO.read(CARS_IMPORT_JSON)));
      //  consoleIO.write(districtController.importDataFromJSON(fileIO.read(DISTRICTS_IMPORT_JSON)));
      //  consoleIO.write(racerController.importDataFromJSON(fileIO.read(RACERS_IMPORT_JSON)));
        //fileIO.write(townController.exportTownsWithAnyRacers(), TOWNS_EXPORT_JSON);
        //fileIO.write(racerController.exportRacersWithAnyCars(),RACERS_EXPORT_JSON);
        consoleIO.write(raceEntryController.importDataFromXML(fileIO.read(RACE_ENTRIES_IMPORT_XML)));

    }
}
