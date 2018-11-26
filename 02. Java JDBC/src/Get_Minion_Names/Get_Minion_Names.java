package Get_Minion_Names;

import java.sql.*;
import java.util.Scanner;

public class Get_Minion_Names {

    public static void main(String[] args) throws SQLException {

            Scanner scanner = new Scanner(System.in);
            int givenId = Integer.parseInt(scanner.nextLine());

        String URL = "jdbc:mysql://localhost:3306/MinionsDB?createDatabaseIfNotExist=true";
        String USER = "root";
        String PASSWORD = "";

        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);

        String countQuerry = "SELECT * FROM villains";
        String minionQuerry = "SELECT name FROM minions AS m\n" +
                "JOIN minions_villains AS mv\n" +
                "ON m.minion_id = mv.minion_id\n" +
                "WHERE villain_id = ?;";

        Statement stmt = conn.createStatement();

        ResultSet rsCount = stmt.executeQuery(countQuerry);

        int count = 0;

        boolean villainFound = true;

        while(rsCount.next()) {
            count++;
            if(count == givenId) {
                villainFound = false;
                System.out.println("Villain: "+ rsCount.getString("name"));
            }
        }
        if(villainFound) {
            System.out.println("No villain with ID 10 exists in the database.");
        }
        else {
            PreparedStatement stmtMinion = conn.prepareStatement(minionQuerry);
            stmtMinion.setInt(1,givenId);
            ResultSet rsMinion = stmtMinion.executeQuery();

            if(!rsMinion.isBeforeFirst()) {
                System.out.println("<no minions>");
            }

            int ct = 1;

            while(rsMinion.next()) {
                System.out.println(ct++ +". "+rsMinion.getString("name"));
            }
        }
    }
}
