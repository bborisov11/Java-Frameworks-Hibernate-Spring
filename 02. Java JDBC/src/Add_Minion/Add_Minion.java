package Add_Minion;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Add_Minion {
    public static void main(String[] args) throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/MinionsDB?createDatabaseIfNotExist=true";
        String USER = "root";
        String PASSWORD = "";

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        Scanner scanner = new Scanner(System.in);

        String [] minions = Arrays.stream(scanner.nextLine().split("\\s+")).skip(1L).toArray(String[]::new);
        String nameOfMinion = minions[0];
        String years = minions[1];
        String town = minions[2];

        String[] villains = Arrays.stream(scanner.nextLine().split("\\s+")).skip(1L).toArray(String[]::new);

        String villain = villains[0];

        String townsCurrentId = "SELECT town_id FROM towns\n" +
                "WHERE name LIKE ?;";
        String villainsCurrentId = "SELECT villain_id FROM villains\n" +
                "WHERE name LIKE ?;";

        String towns = "SELECT name FROM towns WHERE name LIKE ?;";
        String updateTown = "INSERT INTO towns (name,information) VALUES\n" +
                "(?,'asd');";

        String villainsStr = "SELECT name FROM villains WHERE name LIKE ?;";
        String updateVillain = "INSERT INTO villains (name,evilness_factor) VALUES\n" +
                "(?,'evil');";

        PreparedStatement stmtTown = conn.prepareStatement(towns);
        stmtTown.setString(1,town);
        ResultSet rs = stmtTown.executeQuery();

        if(!rs.isBeforeFirst()) {
            PreparedStatement townStmt = conn.prepareStatement(updateTown);
            townStmt.setString(1,town);
            townStmt.executeUpdate();
            System.out.println("Town "+town+ " was added to the database.");
        }
        //get townID
        PreparedStatement stmtTownId = conn.prepareStatement(townsCurrentId);
        stmtTownId.setString(1,town);
        ResultSet rsTownsId = stmtTownId.executeQuery();
        rsTownsId.next();
        int townId = rsTownsId.getInt(1);

        PreparedStatement stmtVillains = conn.prepareStatement(villainsStr);
        stmtVillains.setString(1,villain);
        ResultSet rsVillain = stmtVillains.executeQuery();

        if(!rsVillain.isBeforeFirst()) {
            PreparedStatement villStmt = conn.prepareStatement(updateVillain);
            villStmt.setString(1,villain);
            villStmt.executeUpdate();
            System.out.println("Villain "+villain+" was added to the database.");
        }
        //get villainsID
        PreparedStatement stmtVillainID = conn.prepareStatement(villainsCurrentId);
        stmtVillainID.setString(1,villain);
        stmtVillainID.getMetaData();


        ResultSet rsVillainID = stmtVillainID.executeQuery();
        rsVillainID.next();
        int villainsID = rsVillainID.getInt(1);

        String minionUpdate = "INSERT INTO villains (name,age,town_id) VALUES\n" +
                "(?,?,?);";
        PreparedStatement stmtMinion = conn.prepareStatement(minionUpdate);
        stmtMinion.setString(1,nameOfMinion);
        stmtMinion.setString(2,years);
        stmtMinion.setInt(3,townId);

        String minionVillainUpdate = "INSERT INTO minions_villains(minion_id,villain_id) VALUES (?,?)";
        PreparedStatement stmtMinionVillain = conn.prepareStatement(minionVillainUpdate);
        stmtMinionVillain.setInt(1,townId);
        stmtMinionVillain.setInt(2,villainsID);
        System.out.printf("Successfully added %s to be minion of %s",nameOfMinion,villain);

    }
}
