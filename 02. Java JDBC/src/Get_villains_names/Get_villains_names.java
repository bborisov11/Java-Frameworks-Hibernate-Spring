package Get_villains_names;

import java.sql.*;

public class Get_villains_names {
    public static void main(String[] args) throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/MinionsDB?createDatabaseIfNotExist=true";
        String USER = "root";
        String PASSWORD = "";

        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);

        String villCount = "SELECT CONCAT(t.name, ' ' , COUNT(t.villain_id)) AS output FROM\n" +
                "(SELECT v.villain_id,v.name,mv.minion_id FROM minions_villains AS mv \n" +
                "JOIN villains AS v\n" +
                "ON mv.villain_id = v.villain_id) AS t\n" +
                "GROUP BY t.name\n" +
                "HAVING COUNT(t.villain_id) > 3";

        Statement stmt = conn.createStatement();

        ResultSet rst = stmt.executeQuery(villCount);

        while(rst.next()) {
            System.out.println(rst.getString("output"));
        }
        conn.close();
    }
}
