package Initial_Setup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Initial_setup {
        public static void main(String[] args) throws SQLException {

            String URL = "jdbc:mysql://localhost:3306/MinionsDB?createDatabaseIfNotExist=true";
            String USER = "root";
            String PASSWORD = "";

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String townsTable = "CREATE TABLE towns (\n" +
                    "town_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                    "name VARCHAR(50) NOT NULL,\n" +
                    "information VARCHAR(255) NOT NULL\n" +
                    ");";

            String minionsTable = "CREATE TABLE minions (\n" +
                    "minion_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                    "name VARCHAR(50) NOT NULL, \n"+
                    "age INT NOT NULL, \n" +
                    "town_id INT NOT NULL\n" +
                    ");";

            String villainsTable = "CREATE TABLE villains (\n" +
                    "villain_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                    "name VARCHAR(50) NOT NULL,\n" +
                    "evilness_factor ENUM('good','bad','evil','super evil')\n" +
                    ");";

            String foreignTownsMinions = "ALTER TABLE minions ADD CONSTRAINT fk_towns_id FOREIGN KEY (town_id) REFERENCES towns(town_id);";

            String villainsMinionsTable = "CREATE TABLE minions_villains (\n" +
                    "minion_id INT ,\n" +
                    "villain_id INT ,\n" +
                    "PRIMARY KEY(minion_id,villain_id),\n" +
                    "FOREIGN KEY(minion_id)\n" +
                    "REFERENCES minions(minion_id),\n" +
                    "FOREIGN KEY(villain_id)\n" +
                    "REFERENCES villains(villain_id)\n" +
                    ");";

            String fillTowns = "INSERT INTO towns(name,information) VALUES\n" +
                    "('Sofia','Stolica'),('Plovdiv','Qk grad'),('Varna','Plajove'),('Burgas','Oshte plajove'),('Vraca','Pustinqci');";

            String fillMinions = "INSERT INTO minions(name,age,town_id) VALUES\n" +
                    "('John',12,1),('Kris',13,3),('Anthony',12,2),('Maria',11,1),('Anastasia',2,3);";

            String fillVillains = "INSERT INTO villains(name,evilness_factor) VALUES\n" +
                    "('Pesho','evil'),('Gosho','super evil'),('Kiro','good'),('Parkash','bad'),('Stamen','good');";

            String fillVillainsMinion = "INSERT INTO minions_villains(minion_id,villain_id) VALUES\n" +
                                            "(1,2),(2,2),(3,2),(4,1),(5,3);";

            PreparedStatement stmtMinTable = conn.prepareStatement(minionsTable);
            PreparedStatement stmtTownTable = conn.prepareStatement(townsTable);
            PreparedStatement stmtVillainTable = conn.prepareStatement(villainsTable);
            PreparedStatement stmtForeignTownsMinions = conn.prepareStatement(foreignTownsMinions);
            PreparedStatement stmtVillainsMinionsTable = conn.prepareStatement(villainsMinionsTable);
            PreparedStatement stmtFillTowns = conn.prepareStatement(fillTowns);
            PreparedStatement stmtFillMinions = conn.prepareStatement(fillMinions);
            PreparedStatement stmtFillVillains = conn.prepareStatement(fillVillains);
            PreparedStatement stmtfillVillainsMinion = conn.prepareStatement(fillVillainsMinion);


            stmtMinTable.executeUpdate(minionsTable);
            stmtTownTable.executeUpdate(townsTable);
            stmtVillainTable.executeUpdate(villainsTable);
            stmtForeignTownsMinions.executeUpdate(foreignTownsMinions);
            stmtVillainsMinionsTable.executeUpdate(villainsMinionsTable);
            stmtFillTowns.executeUpdate(fillTowns);
            stmtFillMinions.executeUpdate(fillMinions);
            stmtFillVillains.executeUpdate(fillVillains);
            stmtfillVillainsMinion.executeUpdate(fillVillainsMinion);
            conn.close();

        }
    }
